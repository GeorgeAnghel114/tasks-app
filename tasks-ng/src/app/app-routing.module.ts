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
    data:{
      title:'Home'
    }
  },
  {
    path:'register',
    component:RegisterFormComponent,
    data:{
      title:'Register'
    }
  },
  {
    path:'login',
    component:LoginFormComponent,
    data:{
      title:'Login'
    }
  },
  {
    path:'',
    component:LoginFormComponent,
    data:{
      title:'Login'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
