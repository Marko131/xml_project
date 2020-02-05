import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./login/login.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RegisterComponent } from "./register/register.component";
import { NavbarComponent } from "./navbar/navbar.component";
import {
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatToolbarModule,
  MatButtonToggleModule,
  MatTabsModule,
  MatSnackBarModule,
  MatIconModule,
  MatExpansionModule,
  MatSelectModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatCardModule,
  MatListModule,
  MatGridListModule,
  MatTableModule,
  MatRadioModule,
  MatChipsModule,
  MatMenuModule,
  MatDialogModule,
  MatCheckboxModule
} from "@angular/material";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { AllowedRoutes } from "./_services/allowedRoutes.service";
import { MainComponent } from "./main/main.component";
import { AuthService } from "./_services/auth.service";
import { ProfileComponent } from "./profile/profile.component";
import { PaperService } from "./_services/paper.service";
import { CoverLetterService } from "./_services/coverLetter.service";
import { RdfService } from "./_services/rdf.service";
import { ReviwerProfileComponent } from "./reviwer-profile/reviwer-profile.component";
import { ReviewerGuard } from "./_helpers/reviewer.guard";
import { ReviewService } from "./_services/review.service";
import { EditorProfileComponent } from "./editor-profile/editor-profile.component";
import { EditorGuard } from "./_helpers/editor.guard";
import { ReviewerFunctionsComponent } from "./reviewer-functions/reviewer-functions.component";
import { EditorFunctionsComponent } from "./editor-functions/editor-functions.component";
import { SelectReviewersDialogComponent } from "./select-reviewers-dialog/select-reviewers-dialog.component";
import { RawInputDialogComponent } from "./raw-input-dialog/raw-input-dialog.component";
import { XmlEditComponent } from "./xml-edit/xml-edit.component";
import { PreviewComponent } from './preview/preview.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    MainComponent,
    ProfileComponent,
    ReviwerProfileComponent,
    EditorProfileComponent,
    ReviewerFunctionsComponent,
    EditorFunctionsComponent,
    RawInputDialogComponent,
    SelectReviewersDialogComponent,
    XmlEditComponent,
    PreviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
    MatButtonToggleModule,
    MatTabsModule,
    MatSnackBarModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatExpansionModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatListModule,
    MatGridListModule,
    MatTableModule,
    MatRadioModule,
    MatChipsModule,
    MatMenuModule,
    MatDialogModule,
    MatCheckboxModule
  ],
  providers: [
    AllowedRoutes,
    AuthService,
    PaperService,
    CoverLetterService,
    RdfService,
    ReviewService,
    ReviewerGuard,
    EditorGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  entryComponents: [RawInputDialogComponent, SelectReviewersDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule {}
