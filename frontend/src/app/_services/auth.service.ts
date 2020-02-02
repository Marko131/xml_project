import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { User } from "../_models/user.model";
import { RegisterUser } from "../_models/registerUser.model";

@Injectable()
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http.post(
      `${environment.apiUrl}/api/login`,
      { email: email, password: password },
      { responseType: "text" }
    );
  }

  register(user: RegisterUser) {
    return this.http.post(
      `${environment.apiUrl}/api/register`,
      {
        ...user
      },
      { responseType: "text" }
    );
  }
}
