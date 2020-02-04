import { Component, OnInit } from "@angular/core";
import { PaperService } from "../_services/paper.service";
import { ReviewService } from "../_services/review.service";
import { MatSnackBar } from "@angular/material";

@Component({
  selector: "app-reviewer-functions",
  templateUrl: "./reviewer-functions.component.html",
  styleUrls: ["./reviewer-functions.component.css"]
})
export class ReviewerFunctionsComponent implements OnInit {
  papersForReview: Array<any>;
  review: File;

  displayedColumns = ["title", "preview", "acceptOrReject"];
  constructor(
    private paperService: PaperService,
    private reviewService: ReviewService,
    private _snackBar: MatSnackBar
  ) {
    this.review = null;
  }

  ngOnInit() {
    this.paperService
      .getPapersForReview()
      .subscribe(response => (this.papersForReview = response));
  }

  accept(paperTitle: string) {
    console.log(paperTitle);
  }
  reject(paperTitle: string) {
    console.log(paperTitle);
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
