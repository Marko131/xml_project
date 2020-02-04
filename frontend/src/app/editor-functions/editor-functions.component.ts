import { Component, OnInit } from "@angular/core";
import { PaperService } from "../_services/paper.service";
import { EditorPaper } from "../_models/editorPaper.model";
import { MatDialog } from "@angular/material";
import { SelectReviewersDialogComponent } from "../select-reviewers-dialog/select-reviewers-dialog.component";

@Component({
  selector: "app-editor-functions",
  templateUrl: "./editor-functions.component.html",
  styleUrls: ["./editor-functions.component.css"]
})
export class EditorFunctionsComponent implements OnInit {
  papers: Array<EditorPaper>;
  displayedColumns = ["title", "status", "action", "reviewers"];
  constructor(private paperService: PaperService, public dialog: MatDialog) {}

  ngOnInit() {
    this.paperService
      .getPapersForEditor()
      .subscribe(response => (this.papers = response));
  }

  openDialog(paperTitle: String): void {
    const dialogRef = this.dialog.open(SelectReviewersDialogComponent, {
      data: { paperTitle: paperTitle }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log("The dialog was closed");
    });
  }

  publish(paperTitle: string) {
    console.log(paperTitle);
  }

  reject(paperTitle: string) {
    console.log(paperTitle);
  }
}
