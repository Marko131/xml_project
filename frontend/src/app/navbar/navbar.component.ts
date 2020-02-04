import { Component, OnInit } from "@angular/core";
import { AllowedRoutes } from "../_services/allowedRoutes.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  navLinks = [];
  isLoggedIn: boolean;
  constructor(private allowedRoutes: AllowedRoutes, private router: Router) {
    this.allowedRoutes.currentRoutes.subscribe(routes => {
      this.navLinks = routes;
    });
  }

  ngOnInit() {
    this.allowedRoutes.currentRoutes.subscribe(routes => {
      this.navLinks = routes;
    });
    this.allowedRoutes.isLoggedInObservable.subscribe(
      isLoggedIn => (this.isLoggedIn = isLoggedIn)
    );
  }

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(["/login"]);
    this.allowedRoutes.updateRoutes();
  }
}
