import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  selector: "app-raw-input-dialog",
  templateUrl: "./raw-input-dialog.component.html",
  styleUrls: ["./raw-input-dialog.component.css"]
})
export class RawInputDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<RawInputDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
