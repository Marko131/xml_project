import { Injectable } from "@angular/core";
import {
  CanActivate,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from "@angular/router";
import { AuthService } from "../_services/auth.service";
import { MatSnackBar } from "@angular/material";
import * as jwt_decode from "jwt-decode";

@Injectable({ providedIn: "root" })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthService,
    private _snackBar: MatSnackBar
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
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
      return true;
    }
  }
}
