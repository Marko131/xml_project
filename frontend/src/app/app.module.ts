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
  MatMenuModule
} from "@angular/material";
import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { AllowedRoutes } from "./_services/allowedRoutes.service";
import { MainComponent } from "./main/main.component";
import { AuthService } from "./_services/auth.service";
import { ProfileComponent } from "./profile/profile.component";
import { PaperService } from "./_services/paper.service";
import { CoverLetterService } from "./_services/coverLetter.service";
import { RdfService } from "./_services/rdf.service";
import { ReviwerProfileComponent } from "./reviwer-profile/reviwer-profile.component";
import { AuthGuard } from "./_helpers/auth.guard";
import { ReviewerGuard } from "./_helpers/reviewer.guard";
import { ReviewService } from "./_services/review.service";
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    MainComponent,
    ProfileComponent,
    ReviwerProfileComponent
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
    MatMenuModule
  ],
  providers: [
    AllowedRoutes,
    AuthService,
    PaperService,
    CoverLetterService,
    RdfService,
    ReviewService,
    ReviewerGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
