import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { MatSnackBar } from "@angular/material";
import * as jwt_decode from "jwt-decode";

@Injectable()
export class EditorGuard implements CanActivate {
  constructor(private router: Router, private _snackBar: MatSnackBar) {}
  canActivate(): boolean {
    let auth = false;
    const token = localStorage.getItem("token");
    if (!token) {
      this.router.navigate(["/login"]);
      this._snackBar.open("Login first", "", { duration: 1000 });
      return false;
    } else {
      const decodedToken = jwt_decode(token);
      let current_time = new Date().getTime() / 1000;
      if (current_time > decodedToken.exp) {
        this.router.navigate(["/login"]);
        this._snackBar.open("Login first", "", { duration: 1000 });
        return false;
      }
      decodedToken.roles.forEach(role => {
        if (role.authority === "ROLE_EDITOR") {
          auth = true;
        }
      });
      if (!auth) {
        this._snackBar.open("Unauthorized", "", { duration: 1000 });
        this.router.navigate(["/profile"]);
      }
      return auth;
    }
  }
}
