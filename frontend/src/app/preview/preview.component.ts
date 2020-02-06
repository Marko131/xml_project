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
  type: string;
  constructor(
    private route: ActivatedRoute,
    private paperService: PaperService
  ) {
    this.route.params.subscribe(params => {
      this.documentId = params["id"];
      this.type = params["type"];
    });
  }

  ngOnInit() {
    if (this.type === "anonymous") {
      this.paperService
        .previewAnonymous(this.documentId)
        .subscribe(response => (this.content = response));
    } else {
      this.paperService
        .preview(this.documentId)
        .subscribe(response => (this.content = response));
    }
  }
}
