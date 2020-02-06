import { Component, OnInit } from "@angular/core";
import { AllowedRoutes } from "../_services/allowedRoutes.service";
import { FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { PaperService } from "../_services/paper.service";
import { Router } from "@angular/router";
import { ENTER, COMMA } from "@angular/cdk/keycodes";
import { MatChipInputEvent } from "@angular/material";

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html",
  styleUrls: ["./main.component.css"]
})
export class MainComponent implements OnInit {
  searchForm: FormGroup;
  advancedSearchForm: FormGroup;

  keywords: Array<String>;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  papers: Array<string>;
  constructor(
    private allowedRoutes: AllowedRoutes,
    private fb: FormBuilder,
    private paperService: PaperService,
    private router: Router
  ) {
    this.searchForm = fb.group({
      search: new FormControl("")
    });

    this.advancedSearchForm = fb.group({
      title: new FormControl(""),
      author: new FormControl(""),
      keywords: new FormControl("")
    });

    this.papers = new Array<string>();
    this.keywords = new Array<string>();
  }

  ngOnInit() {
    this.allowedRoutes.updateRoutes();
  }

  onSubmit(form: FormGroup) {
    const text = form.controls["search"].value;
    this.paperService.basicSearch(text).subscribe(
      response => {
        this.papers = response;
        console.log(response);
      },
      errorResponse => console.log(errorResponse)
    );
  }

  onSubmit2(form: FormGroup) {
    let request = {
      keyword: this.keywords,
      ...form.value
    };
    this.paperService.advancedSearch(request).subscribe(
      response => {
        let papersList = response.results.bindings.map(
          element => element.title.value
        );
        this.papers = papersList.filter(
          (item, pos, self) => self.indexOf(item) == pos
        );
      },
      errorResponse => console.log(errorResponse)
    );
  }

  preview(paperTitle: string) {
    this.router.navigate(["/preview", paperTitle, "text"]);
  }

  addKeyword(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || "").trim()) {
      this.keywords.push(value);
    }
    if (input) {
      input.value = "";
    }
  }
  remove(index: number): void {
    if (index >= 0) {
      this.keywords.splice(index, 1);
    }
  }
}
