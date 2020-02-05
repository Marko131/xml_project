import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { ReviewersForPaper } from "../_models/reviewersForPaper.model";
import { of } from "rxjs";
import { Reviewer } from "../_models/reviewer.model";

@Injectable()
export class ReviewService {
  constructor(private http: HttpClient) {}

  upload(review: File) {
    const formData: FormData = new FormData();
    formData.append("file", review, review.name);
    return this.http.post(`${environment.apiUrl}/api/review`, formData);
  }

  getReviews(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/merge/${paperTitle}`,
      { responseType: "text"}
    );
  }
}
