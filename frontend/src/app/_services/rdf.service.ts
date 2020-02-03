import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable()
export class RdfService {
  constructor(private http: HttpClient) {}

  upload(rdf: File) {
    const formData: FormData = new FormData();
    formData.append("file", rdf, rdf.name);
    return this.http.post(`${environment.apiUrl}/api/rdf`, formData);
  }
}
