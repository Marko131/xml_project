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
  save(paper: string) {
    return this.http.post(`${environment.apiUrl}/api/paperForm`, paper);
  }
  getByUserName() {
    const papers = this.http.get<Array<EditorPaper>>(
      `${environment.apiUrl}/api/getUserPapers`,
    );
    papers.subscribe(response=>console.log(response));
    return papers;
  }
  getPapersForReview() {
    const papers = this.http.get<Array<EditorPaper>>(
      `${environment.apiUrl}/api/getAssignments`,
    );
    return papers;
  }
  getPapersForEditor(): Observable<Array<EditorPaper>> {
    const papers = this.http.get<Array<EditorPaper>>(
      `${environment.apiUrl}/api/paper`,
    );
    return papers;
  }
  downloadXml(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/download/xml/${paperTitle}`,
      { responseType: "text"}
    );
  }
  downloadPdf(paperTitle: string) {
    return of("PDF successfully downloaded");
  }
  archive(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/archive/${paperTitle}`,
      { responseType: "text"}
    );
  }
  publish(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/publish/${paperTitle}`,
      { responseType: "text"}
    );
  }
  preview(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/preview/${paperTitle}`,
      { responseType: "text"}
    );
  }
  reject(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/reject/${paperTitle}`,
      { responseType: "text"}
    );
  }
}
