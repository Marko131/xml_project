import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { User } from "../_models/user.model";
import { RegisterUser } from "../_models/registerUser.model";
import { Observable, of } from "rxjs";
import { Reviewer } from "../_models/reviewer.model";

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

  getReviewers(): Observable<Array<Reviewer>> {
    const reviewers = [
      { name: "FirstName1 LastName1", expertise: ["ex1", "ex2", "ex3", "ex5"] },
      { name: "FirstName2 LastName2", expertise: ["ex3", "ex2"] },
      { name: "FirstName3 LastName3", expertise: ["ex2", "ex1", "ex4"] },
      {
        name: "FirstName4 LastName4",
        expertise: ["ex5", "ex1", "ex3", "ex2", "ex7"]
      },
      { name: "FirstName5 LastName5", expertise: ["ex3", "ex2", "ex6"] }
    ];
    return of(
      reviewers.map(reviewer => new Reviewer(reviewer.name, reviewer.expertise))
    );
  }
}
