import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { AuthService } from "../_services/auth.service";
import { Router } from "@angular/router";
import { AllowedRoutes } from "../_services/allowedRoutes.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private allowedRoutes: AllowedRoutes
  ) {
    this.form = this.fb.group({
      email: ["", Validators.required],
      password: ["", Validators.required]
    });
  }
  login() {
    const val = this.form.value;

    if (val.email && val.password) {
      this.authService.login(val.email, val.password).subscribe(
        token => {
          localStorage.setItem("token", token);
          this.router.navigate(["/"]);
          this.allowedRoutes.updateRoutes();
        },
        response => {
          try {
            let errorResponse = JSON.parse(response.error);
            let errorAlert = "";
            errorResponse.errors.forEach(err => {
              errorAlert += `${err.defaultMessage}\n`;
            });
            alert(errorAlert);
          } catch (err) {
            alert(response.error);
          }
        }
      );
    }
  }

  ngOnInit() {}
}
