import { Component, OnInit } from "@angular/core";
import { AllowedRoutes } from "../_services/allowedRoutes.service";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  navLinks = [];
  constructor(private routes: AllowedRoutes) {
    this.routes.currentRoutes.subscribe(routes => {
      this.navLinks = routes;
    });
  }

  ngOnInit() {
    this.routes.currentRoutes.subscribe(routes => {
      this.navLinks = routes;
    });
  }
}
