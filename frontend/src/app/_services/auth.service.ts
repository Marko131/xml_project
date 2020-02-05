import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { User } from "../_models/user.model";
import { RegisterUser } from "../_models/registerUser.model";
import { Observable, of } from "rxjs";
import { Reviewer } from "../_models/reviewer.model";
import { ReviewersForPaper } from "../_models/reviewersForPaper.model";

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
    return this.http.get<Array<Reviewer>>(
      `${environment.apiUrl}/api/getReviewers/`,
    );
  }
  getRecommendedReviewers(paperTitle: string): Observable<Array<Reviewer>> {
    return this.http.get<Array<Reviewer>>(
      `${environment.apiUrl}/api/getReviewers/${paperTitle}`,
    );
  }
  selectReviewersForPaper(reviewersForPaper: ReviewersForPaper) {
    return this.http.post(
      `${environment.apiUrl}/api/setReviewers/`,
      reviewersForPaper,
      { responseType: "text" }
    );
  }
}
