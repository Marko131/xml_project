import { Component, OnInit } from "@angular/core";
import { AllowedRoutes } from "../_services/allowedRoutes.service";

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html",
  styleUrls: ["./main.component.css"]
})
export class MainComponent implements OnInit {
  constructor(private allowedRoutes: AllowedRoutes) {}

  ngOnInit() {
    this.allowedRoutes.updateRoutes();
  }
}
