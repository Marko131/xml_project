import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AllowedRoutes } from "../_services/allowedRoutes.service";
import { AuthService } from "../_services/auth.service";
import { PaperService } from "../_services/paper.service";
import { MatSnackBar } from "@angular/material";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  paper: File;
  constructor(
    private router: Router,
    private allowedRoutes: AllowedRoutes,
    private authService: AuthService,
    private paperService: PaperService,
    private _snackBar: MatSnackBar
  ) {
    this.paper = null;
  }

  ngOnInit() {}

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(["/login"]);
    this.allowedRoutes.updateRoutes();
  }

  handleFileInput(files: FileList) {
    this.paper = files.item(0);
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
  }
  remove() {
    this.paper = null;
  }
}
