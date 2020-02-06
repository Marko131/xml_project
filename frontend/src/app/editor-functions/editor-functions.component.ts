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
    this.router.navigate(["preview", paperTitle, "text"]);
  }

  getReviews(paperTitle: string) {
    this.reviewService.getReviews(paperTitle).subscribe(
      response => this.downloadFileXML(response, paperTitle),
      errorResponse => console.log(errorResponse)
    );
  }

  publish(paper: EditorPaper) {
    this.paperService
      .publish(paper.paperTitle)
      .subscribe(response => (paper.paperStatus = "published"));
  }

  reject(paper: EditorPaper) {
    this.paperService
      .archive(paper.paperTitle)
      .subscribe(response => (paper.paperStatus = "archived"));
  }

  downloadFileXML(data: string, paperTitle: string) {
    const blob = new Blob([data], { type: "application/xml" });
    const url = window.URL.createObjectURL(blob);
    var link = document.createElement("a");
    link.href = url;
    link.download = paperTitle + " " + "reviews.xml";
    link.click();
  }
}
