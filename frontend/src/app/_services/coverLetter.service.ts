import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable()
export class CoverLetterService {
  constructor(private http: HttpClient) {}

  upload(coverLetter: File) {
    const formData: FormData = new FormData();
    formData.append("file", coverLetter, coverLetter.name);
    return this.http.post(`${environment.apiUrl}/api/letter`, formData);
  }
  
  save(letter: string) {
    return this.http.post(`${environment.apiUrl}/api/letterForm`, letter);
  }
}
