import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import * as jwt_decode from "jwt-decode";
@Injectable()
export class AllowedRoutes {
  private routes = new BehaviorSubject([]);
  currentRoutes = this.routes.asObservable();
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
      return;
    }
    let decodedToken = jwt_decode(token);

    decodedToken.roles.forEach(role => {
      if (role.authority == "ROLE_REVIEWER") {
        components.push({
          path: "profile",
          label: `Reviewer: ${decodedToken.sub}`
        });
      }
      if (role.authority == "ROLE_AUTHOR") {
        components.push({
          path: "profile",
          label: `Author: ${decodedToken.sub}`
        });
      }
      if (role.authority == "ROLE_EDITOR") {
        components.push({
          path: "profile",
          label: `Editor: ${decodedToken.sub}`
        });
      }
    });
    this.routes.next(components);
  }
}
