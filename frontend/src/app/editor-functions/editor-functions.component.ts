import { Component, OnInit } from "@angular/core";
import { PaperService } from "../_services/paper.service";
import { EditorPaper } from "../_models/editorPaper.model";
import { MatDialog } from "@angular/material";
import { RawInputDialogComponent } from "../raw-input-dialog/raw-input-dialog.component";
import { SelectReviewersDialogComponent } from "../select-reviewers-dialog/select-reviewers-dialog.component";
import { Router } from "@angular/router";
import { ReviewService } from "../_services/review.service";
import { error } from "protractor";

@Component({
  selector: "app-editor-functions",
  templateUrl: "./editor-functions.component.html",
  styleUrls: ["./editor-functions.component.css"]
})
export class EditorFunctionsComponent implements OnInit {
  papers: Array<EditorPaper>;
  displayedColumns = [
    "title",
    "status",
    "preview",
    "reviewers",
    "reviews",
    "action"
  ];
  constructor(
    private paperService: PaperService,
    private reviewService: ReviewService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.paperService
      .getPapersForEditor()
      .subscribe(response => (this.papers = response));
  }

  openDialog(paperTitle: String): void {
    const dialogRef = this.dialog.open(SelectReviewersDialogComponent, {
      data: { paperTitle: paperTitle }
    });

    dialogRef.afterClosed().subscribe(result => {});
  }

  preview(paperTitle: string) {
    this.router.navigate(["preview", paperTitle]);
  }

  getReviews(paperTitle: string) {
    this.reviewService.getReviews(paperTitle).subscribe(
      response => console.log(response),
      errorResponse => console.log(errorResponse)
    );
  }

  publish(paper: EditorPaper) {
    paper.paperStatus = "published";
  }

  reject(paper: EditorPaper) {
    paper.paperStatus = "rejected";
  }
}
