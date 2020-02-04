import { Component, OnInit } from "@angular/core";
import { AllowedRoutes } from "../_services/allowedRoutes.service";
import { FormBuilder, FormGroup } from "@angular/forms";

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html",
  styleUrls: ["./main.component.css"]
})
export class MainComponent implements OnInit {
  searchForm: FormGroup;
  constructor(private allowedRoutes: AllowedRoutes, private fb: FormBuilder) {
    this.searchForm = fb.group({
      search: ""
    });
  }

  ngOnInit() {
    this.allowedRoutes.updateRoutes();
  }

  onSubmit(form: FormGroup) {
    console.log(form.value);
  }
}
