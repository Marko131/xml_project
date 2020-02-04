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
      { title: "Paper1.xml" },
      { title: "Paper2.xml" },
      { title: "Paper3.xml" },
      { title: "Paper4.xml" },
      { title: "Paper5.xml" }
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
}
