import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable()
export class ReviewService {
  constructor(private http: HttpClient) {}

  upload(review: File) {
    const formData: FormData = new FormData();
    formData.append("file", review, review.name);
    return this.http.post(`${environment.apiUrl}/api/review`, formData);
  }
}
