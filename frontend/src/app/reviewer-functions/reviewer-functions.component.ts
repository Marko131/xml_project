import { Component, OnInit } from "@angular/core";
import { PaperService } from "../_services/paper.service";
import { ReviewService } from "../_services/review.service";
import { MatSnackBar } from "@angular/material";
import { Router } from "@angular/router";

@Component({
  selector: "app-reviewer-functions",
  templateUrl: "./reviewer-functions.component.html",
  styleUrls: ["./reviewer-functions.component.css"]
})
export class ReviewerFunctionsComponent implements OnInit {
  papersForReview: Array<any>;
  review: File;

  displayedColumns = ["title", "preview", "status", "acceptOrReject"];
  constructor(
    private paperService: PaperService,
    private reviewService: ReviewService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {
    this.review = null;
  }

  ngOnInit() {
    this.paperService
      .getPapersForReview()
      .subscribe(response => (this.papersForReview = response));
  }

  reject(paper: any) {
    this.paperService
      .reject(paper.paperTitle)
      .subscribe(
        response =>
          (this.papersForReview = this.papersForReview.filter(
            p => p.paperTitle != paper.paperTitle
          ))
      );
  }
  preview(paperTitle: string) {
    this.router.navigate(["/preview", paperTitle, "anonymous"]);
  }

  handleReviewInput(files: FileList) {
    this.review = files.item(0);
  }
  removeReview() {
    this.review = null;
  }
  uploadFile() {
    if (!this.review) return;
    this.reviewService.upload(this.review).subscribe(
      data => {
        this._snackBar.open("File has been successfully uploaded", "", {
          duration: 2000
        });
      },
      error => {
        this._snackBar.open(error.error.message, "", {
          duration: 2000
        });
      }
    );
  }
}
