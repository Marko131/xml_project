import { Component } from "@angular/core";
import { AllowedRoutes } from "./_services/allowedRoutes.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent {
  title = "xmlFrontend";
  constructor(private allowedRoutes: AllowedRoutes) {
    allowedRoutes.updateRoutes();
  }
}
