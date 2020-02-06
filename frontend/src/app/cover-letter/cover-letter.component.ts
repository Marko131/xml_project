import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material";
import { RawInputDialogComponent } from "../raw-input-dialog/raw-input-dialog.component";
import { letterSpec } from "../_helpers/coverLetterSpec";
import { CoverLetterService } from '../_services/coverLetter.service';
declare let Xonomy: any;
@Component({
  selector: "app-cover-letter",
  templateUrl: "./cover-letter.component.html",
  styleUrls: ["./cover-letter.component.css"]
})
export class CoverLetterComponent implements OnInit {
  xml: string;

  constructor(
    public dialog: MatDialog,
    private coverLetterService: CoverLetterService) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(RawInputDialogComponent, {
      width: "1000px",
      data: { xml: this.xml }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.xml = result;
      this.xonomy();
    });
  }
  ngOnInit() {
    this.xonomy();
  }

  save() {
    let editor = document.getElementById("editor");
    let text = Xonomy.harvest();
    console.log(text);
    this.coverLetterService.save(text).subscribe(response => console.log(response));
  }

  xonomy() {
    let editor = document.getElementById("editor");
    if (!this.xml) this.xml = '<cover_letter><sender><name></name><email></email><phone_number></phone_number><institution></institution><address></address></sender><receiver><name></name><email></email><phone_number></phone_number><institution></institution><address></address></receiver><paragraphs><paragraph></paragraph></paragraphs><signature></signature></cover_letter>';
    console.log(letterSpec);
    Xonomy.render(this.xml, editor, letterSpec);
  }
}
