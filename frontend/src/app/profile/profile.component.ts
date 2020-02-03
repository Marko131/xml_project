import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AllowedRoutes } from "../_services/allowedRoutes.service";
import { AuthService } from "../_services/auth.service";
import { PaperService } from "../_services/paper.service";
import { MatSnackBar } from "@angular/material";
import { CoverLetterService } from "../_services/coverLetter.service";
import { RdfService } from "../_services/rdf.service";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  paper: File;
  coverLetter: File;
  rdf: File;

  userPapers: Array<any>;
  displayedColumns: string[] = ["title", "status", "archive", "preview", "pdf"];
  constructor(
    private router: Router,
    private allowedRoutes: AllowedRoutes,
    private coverLetterService: CoverLetterService,
    private paperService: PaperService,
    private rdfService: RdfService,
    private _snackBar: MatSnackBar
  ) {
    this.paper = null;
  }

  ngOnInit() {
    this.paperService
      .getByUserName()
      .subscribe(response => (this.userPapers = response));
  }

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(["/login"]);
    this.allowedRoutes.updateRoutes();
  }

  handlePaperInput(files: FileList) {
    this.paper = files.item(0);
  }
  handleCoverLetterInput(files: FileList) {
    this.coverLetter = files.item(0);
  }
  handleRdfInput(files: FileList) {
    this.rdf = files.item(0);
  }
  removePaper() {
    this.paper = null;
  }
  removeCoverLetter() {
    this.coverLetter = null;
  }
  removeRdf() {
    this.rdf = null;
  }

  uploadFile() {
    if (!this.paper) return;
    this.paperService.upload(this.paper).subscribe(
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
    if (this.coverLetter)
      this.coverLetterService.upload(this.coverLetter).subscribe(
        response =>
          this._snackBar.open("File has been successfully uploaded", "", {
            duration: 2000
          }),
        error => console.log(error)
      );
    if (this.rdf) {
      this.rdfService.upload(this.rdf).subscribe(
        response =>
          this._snackBar.open("File has been successfully uploaded", "", {
            duration: 2000
          }),
        error => console.log(error)
      );
    }
  }
  archive(paperTitle: string) {
    console.log(paperTitle);
  }
  preview(paperTitle: string) {
    console.log(paperTitle);
  }
  downloadPdf(paperTitle: string) {
    console.log(paperTitle);
  }
}
