import { Component, OnInit } from "@angular/core";
import { AllowedRoutes } from "./_services/allowedRoutes.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  title = "xmlFrontend";
  constructor(private allowedRoutes: AllowedRoutes) {
    allowedRoutes.updateRoutes();
  }
  ngOnInit() {
    this.allowedRoutes.updateRoutes();
  }
}
