import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { MainComponent } from "./main/main.component";
import { ProfileComponent } from "./profile/profile.component";
import { AuthGuard } from "./_helpers/auth.guard";
import { ReviwerProfileComponent } from "./reviwer-profile/reviwer-profile.component";
import { ReviewerGuard } from "./_helpers/reviewer.guard";
import { EditorProfileComponent } from "./editor-profile/editor-profile.component";
import { EditorGuard } from "./_helpers/editor.guard";

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "profile", component: ProfileComponent, canActivate: [AuthGuard] },
  {
    path: "reviewer-profile",
    component: ReviwerProfileComponent,
    canActivate: [ReviewerGuard]
  },
  {
    path: "editor-profile",
    component: EditorProfileComponent,
    canActivate: [EditorGuard]
  },
  { path: "", component: MainComponent, pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
