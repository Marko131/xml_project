import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import * as jwt_decode from "jwt-decode";
@Injectable()
export class AllowedRoutes {
  private routes = new BehaviorSubject([]);
  currentRoutes = this.routes.asObservable();
  private login = new BehaviorSubject(true);
  isLoggedInObservable = this.login.asObservable();
  constructor() {
    this.updateRoutes();
  }

  changeRoutes(newRoutes: Array<any>) {
    this.routes.next(newRoutes);
  }

  updateRoutes() {
    let token = localStorage.getItem("token");
    let components = [];
    if (!token) {
      components.push({ path: "login", label: "Login" });
      components.push({ path: "register", label: "Register" });
      this.routes.next(components);
      this.login.next(false);
      return;
    }
    let decodedToken = jwt_decode(token);

    decodedToken.roles.forEach(role => {
      if (role.authority == "ROLE_REVIEWER") {
        components.push({
          path: "text-edit",
          label: "XML"
        });
        components.push({
          path: "cover-letter",
          label: "Cover letter"
        });
        components.push({
          path: "reviewer-profile",
          label: `Reviewer: ${decodedToken.sub}`
        });
      }
      if (role.authority == "ROLE_AUTHOR") {
        components.push({
          path: "text-edit",
          label: "XML"
        });
        components.push({
          path: "cover-letter",
          label: "Cover letter"
        });
        components.push({
          path: "profile",
          label: `Author: ${decodedToken.sub}`
        });
      }
      if (role.authority == "ROLE_EDITOR") {
        components.push({
          path: "text-edit",
          label: "XML"
        });
        components.push({
          path: "cover-letter",
          label: "Cover letter"
        });
        components.push({
          path: "editor-profile",
          label: `Editor: ${decodedToken.sub}`
        });
      }
    });
    this.routes.next(components);
  }

  isLoggedIn(): void {
    let token = localStorage.getItem("token");
    if (!token) this.login.next(false);
    let decodedToken = jwt_decode(token);
    let current_time = new Date().getTime() / 1000;
    if (current_time > decodedToken.exp) this.login.next(false);
    this.login.next(true);
  }
}
