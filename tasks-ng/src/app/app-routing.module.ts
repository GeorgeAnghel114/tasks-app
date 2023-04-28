import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from "./login-form/login-form.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "./_service/auth-guard.service";
import {RegisterFormComponent} from "./register-form/register-form.component";
import {AllTasksComponent} from "./all-tasks/all-tasks.component";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],

  },
  {
    path: 'register',
    component: RegisterFormComponent,

  },
  {
    path: 'login',
    component: LoginFormComponent,

  },
  {
    path: '',
    component: LoginFormComponent,

  },
  {
    path:"all-tasks",
    component: AllTasksComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
