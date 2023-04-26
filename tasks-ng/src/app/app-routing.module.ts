import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent} from "./login-form/login-form.component";
import {HomeComponent} from "./home/home.component";
import { AuthGuard } from "./_service/auth-guard.service";
import {RegisterFormComponent} from "./register-form/register-form.component";

const routes: Routes = [
  {
    path:'home',
    component:HomeComponent,
    canActivate: [AuthGuard],

  },
  {
    path:'register',
    component:RegisterFormComponent,

  },
  {
    path:'login',
    component:LoginFormComponent,

  },
  {
    path:'',
    component:LoginFormComponent,

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
