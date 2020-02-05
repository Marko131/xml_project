import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material";
import { RawInputDialogComponent } from "../raw-input-dialog/raw-input-dialog.component";
declare let Xonomy: any;
@Component({
  selector: "app-cover-letter",
  templateUrl: "./cover-letter.component.html",
  styleUrls: ["./cover-letter.component.css"]
})
export class CoverLetterComponent implements OnInit {
  xml: string;

  constructor(public dialog: MatDialog) {}

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

  xonomy() {
    let editor = document.getElementById("editor");
    const docSpec = {
      elements: {
        xml: {
          menu: [
            {
              caption: "Raw edit",
              action: Xonomy.editRaw,
              actionParameter: {
                fromJs: function(jsElement) {
                  return jsElement.getText();
                },
                toJs: function(txt, origElement) {
                  origElement.addText(txt);
                  return origElement;
                }
              },
              hideIf: function(jsElement) {
                return jsElement.getText() !== "";
              }
            }
          ]
        }
      }
    };
    if (!this.xml) this.xml = `<cover_letter></cover_letter>`;
    Xonomy.render(this.xml, editor, docSpec);
  }
}
