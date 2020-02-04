import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { AuthService } from "../_services/auth.service";
import { Reviewer } from "../_models/reviewer.model";
import { SelectionModel } from "@angular/cdk/collections";
import { ReviewersForPaper } from "../_models/reviewersForPaper.model";

@Component({
  selector: "app-select-reviewers-dialog",
  templateUrl: "./select-reviewers-dialog.component.html",
  styleUrls: ["./select-reviewers-dialog.component.css"]
})
export class SelectReviewersDialogComponent implements OnInit {
  paperTitle: string;
  reviewers: Array<Reviewer>;
  displayedColumns: Array<string>;
  selection = new SelectionModel<Reviewer>(true, []);
  constructor(
    private authService: AuthService,

    private dialogRef: MatDialogRef<SelectReviewersDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {
    this.paperTitle = data.paperTitle;
    this.displayedColumns = ["select", "name", "expertise"];
  }
  ngOnInit(): void {
    this.authService
      .getReviewers()
      .subscribe(response => (this.reviewers = response));
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.reviewers.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.reviewers.forEach(row => this.selection.select(row));
  }

  checkboxLabel(row?: Reviewer): string {
    if (!row) {
      return `${this.isAllSelected() ? "select" : "deselect"} all`;
    }
    return `${
      this.selection.isSelected(row) ? "deselect" : "select"
    } row ${this.reviewers.indexOf(row) + 1}`;
  }
  getRecommended() {
    this.authService.getRecommendedReviewers(this.paperTitle).subscribe(
      response => (this.reviewers = response),
      errorResponse => console.log(errorResponse)
    );
  }
  selectReviewers() {
    const selectedReviewers = this.selection.selected.map(
      reviewer => reviewer.name
    );
    this.dialogRef.close();
    let r = new ReviewersForPaper();
    r.paperTitle = this.paperTitle;
    r.reviewers = selectedReviewers;
    this.authService.selectReviewersForPaper(r).subscribe(
      response => console.log(response),
      errorResponse => console.log(errorResponse)
    );
  }
}
