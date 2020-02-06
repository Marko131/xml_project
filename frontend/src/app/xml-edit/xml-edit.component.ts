import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material";
import { RawInputDialogComponent } from "../raw-input-dialog/raw-input-dialog.component";
import { paperDocSpec } from "../_helpers/scientificPaperSpec";
import { PaperService } from '../_services/paper.service';

declare let Xonomy: any;

@Component({
  selector: "app-xml-edit",
  templateUrl: "./xml-edit.component.html",
  styleUrls: ["./xml-edit.component.css", "../../assets/xonomy/xonomy.css"]
})
export class XmlEditComponent implements OnInit {
  xml: string;

  constructor(
    public dialog: MatDialog,
    private paperService: PaperService) {}

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
    this.paperService.save(text).subscribe(response => console.log(response));
  }

  xonomy() {
    let editor = document.getElementById("editor");
    if (!this.xml) this.xml = `<paper date_received="" date_revised="" date_accepted=""><status><waiting>waiting</waiting></status><paper_title property="pred:title">new paper</paper_title><authors><author about=""><name property="pred:name"></name><university property="pred:works"></university><city property="pred:city"></city><state property="pred:state"></state><country property="pred:country"></country></author></authors><abstract><keywords><keyword property="pred:keyword"></keyword></keywords><abstract_element title=""></abstract_element></abstract><section title=""><paragraph><text></text><annotations></annotations></paragraph></section><references><reference id=""><authors></authors><year></year><title></title><pages></pages></reference></references><corresponding_authors><corresponding_author></corresponding_author></corresponding_authors><citations><citation number=""><authors></authors><year></year><title></title><magazine></magazine><link href=""></link></citation></citations></paper>`;
    console.log(paperDocSpec);
    Xonomy.render(this.xml, editor, paperDocSpec);
  }
}
