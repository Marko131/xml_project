import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
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
    let headers = new HttpHeaders();
    headers = headers.set("Accept", "application/pdf");
    return this.http.get(
      `${environment.apiUrl}/api/paper/download/pdf/${paperTitle}`,
      { headers: headers, responseType: "blob" }
    );
  }
  archive(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/archive/${paperTitle}`,
      { responseType: "text" }
    );
  }
  publish(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/publish/${paperTitle}`,
      { responseType: "text" }
    );
  }
  preview(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/preview/${paperTitle}`,
      { responseType: "text" }
    );
  }
  previewAnonymous(paperTitle: string) {
    return this.http.get(
      `${environment.apiUrl}/api/paper/anonymous/${paperTitle}`,
      { responseType: "text" }
    );
  }
  reject(paperTitle: string) {
    return this.http.get(`${environment.apiUrl}/api/reject/${paperTitle}`, {
      responseType: "text"
    });
  }
  basicSearch(paperTitle: string) {
    let params = new HttpParams().set("text", paperTitle);
    return this.http.get<Array<string>>(
      `${environment.apiUrl}/api/paper/searchByText`,
      {
        params: params
      }
    );
  }
  advancedSearch(request: any) {
    let params = new HttpParams();
    params = params.append("title", request.title);
    params = params.append("author", request.author);
    params = params.append("keyword", request.keyword.join(", "));
    return this.http.get<any>(
      `${environment.apiUrl}/api/paper/advancedSearch`,
      {
        params: params
      }
    );
  }
}
