import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { PaperService } from "../_services/paper.service";

@Component({
  selector: "app-preview",
  templateUrl: "./preview.component.html",
  styleUrls: ["./preview.component.css"]
})
export class PreviewComponent implements OnInit {
  documentId: string;
  content: string;
  constructor(
    private route: ActivatedRoute,
    private paperService: PaperService
  ) {
    this.route.params.subscribe(params => (this.documentId = params["id"]));
  }

  ngOnInit() {
    this.paperService
      .preview(this.documentId)
      .subscribe(response => (this.content = response));
  }
}
