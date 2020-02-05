import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable, of } from "rxjs";
import { EditorPaper } from "../_models/editorPaper.model";

@Injectable()
export class PaperService {
  constructor(private http: HttpClient) {}

  upload(paper: File) {
    const formData: FormData = new FormData();
    formData.append("file", paper, paper.name);
    return this.http.post(`${environment.apiUrl}/api/paper`, formData);
  }
  getByUserName() {
    const papers = this.http.get<Array<EditorPaper>>(
      `${environment.apiUrl}/api/getUserPapers`
    );

    return papers;
  }
  getPapersForReview() {
    const papers = this.http.get<Array<EditorPaper>>(
      `${environment.apiUrl}/api/getAssignments`
    );
    return papers;
  }
  getPapersForEditor(): Observable<Array<EditorPaper>> {
    const papers = this.http.get<Array<EditorPaper>>(
      `${environment.apiUrl}/api/paper`
    );
    return papers;
  }
  downloadXml(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/download/xml/${paperTitle}`,
      { responseType: "text" }
    );
  }
  downloadPdf(paperTitle: string) {
    return of("PDF successfully downloaded");
  }
  archive(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/archive/${paperTitle}`,
      { responseType: "text" }
    );
  }
  preview(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/preview/${paperTitle}`,
      { responseType: "text" }
    );
  }
}
