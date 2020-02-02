import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators, FormArray } from "@angular/forms";
import { MyErrorStateMatcher } from "../_helpers/myErrorStateMatcher";
import { AuthService } from "../_services/auth.service";
import { Router } from "@angular/router";
import { MatChipInputEvent } from "@angular/material";
import { COMMA, ENTER } from "@angular/cdk/keycodes";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  expertise: Array<String>;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(private authService: AuthService, private router: Router) {
    this.registerForm = new FormGroup(
      {
        name: new FormControl("", [Validators.required]),
        lastName: new FormControl("", [Validators.required]),
        email: new FormControl("", [Validators.required, Validators.email]),
        password1: new FormControl("", [
          Validators.required,
          Validators.minLength(6)
        ]),
        password2: new FormControl("", [
          Validators.required,
          Validators.minLength(6)
        ]),
        role: new FormControl("", [Validators.required]),
        expertise: new FormControl("")
      },
      { validators: this.checkPasswords }
    );
    this.expertise = new Array<string>();
  }
  checkPasswords(group: FormGroup) {
    let pass = group.controls.password1.value;
    let confirmPass = group.controls.password2.value;

    return pass === confirmPass ? null : { notSame: true };
  }

  register() {
    let registerUser = {
      ...this.registerForm.value,
      expertise: this.expertise
    };
    console.log(registerUser);
    if (!this.registerForm.valid) return;
    this.authService.register(registerUser).subscribe(
      response => console.log(response),
      errorResponse => console.log(errorResponse)
    );
  }
  addExpertise(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || "").trim()) {
      this.expertise.push(value);
    }

    // Reset the input value
    if (input) {
      input.value = "";
    }
  }
  remove(index: number): void {
    if (index >= 0) {
      this.expertise.splice(index, 1);
    }
  }

  ngOnInit() {}
}
