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
    const paperTitleList = [
      { title: "Paper1.xml", status: "published" },
      { title: "Paper2.xml", status: "archived" },
      { title: "Paper3.xml", status: "waiting" },
      { title: "Paper4.xml", status: "published" },
      { title: "Paper5.xml", status: "archived" },
      { title: "Paper6.xml", status: "published" },
      { title: "Paper7.xml", status: "waiting" },
      { title: "Paper8.xml", status: "published" }
    ];
    return of(paperTitleList);
  }
  getPapersForReview() {
    const papersForReview = [
      { title: "Paper1.xml", status: "acepted" },
      { title: "Paper2.xml", status: "waiting" },
      { title: "Paper3.xml", status: "rejected" },
      { title: "Paper4.xml", status: "waiting" },
      { title: "Paper5.xml", status: "waiting" }
    ];
    return of(papersForReview);
  }
  getPapersForEditor(): Observable<Array<EditorPaper>> {
    const papers = [
      { title: "Paper1.xml", status: "published" },
      { title: "Paper2.xml", status: "waiting" },
      { title: "Paper3.xml", status: "revised" },
      { title: "Paper4.xml", status: "archived" },
      { title: "Paper5.xml", status: "published" },
      { title: "Paper6.xml", status: "waiting" },
      { title: "Paper7.xml", status: "revised" },
      { title: "Paper8.xml", status: "archived" },
      { title: "Paper9.xml", status: "waiting" }
    ];
    const papersForEditor = papers.map(
      paper => new EditorPaper(paper.title, paper.status)
    );
    return of(papersForEditor);
  }
  downloadXml(paperTitle: string) {
    return of("XML successfully downloaded");
  }
  downloadPdf(paperTitle: string) {
    return of("PDF successfully downloaded");
  }
  archive(paperTitle: string) {
    return of("Paper successfully archived");
  }
  preview(paperTitle: string) {
    return of(`<!DOCTYPE html>
    <html>
    <body>
    
    <h2>An Unordered HTML List</h2>
    
    <ul>
      <li>Coffee</li>
      <li>Tea</li>
      <li>Milk</li>
    </ul>  
    
    <h2>An Ordered HTML List</h2>
    
    <ol>
      <li>Coffee</li>
      <li>Tea</li>
      <li>Milk</li>
    </ol> 
    
    </body>
    </html>`);
  }
}
