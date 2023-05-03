import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from "./login-form/login-form.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "./_service/auth-guard.service";
import {RegisterFormComponent} from "./register-form/register-form.component";
import {AllTasksComponent} from "./all-tasks/all-tasks.component";
import {TaskDetailComponent} from "./task-detail/task-detail.component";
import {NewTaskComponent} from "./new-task/new-task.component";

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
    canActivate: [AuthGuard],


  },
  {
    path:"all-tasks",
    component: AllTasksComponent,
    canActivate: [AuthGuard],

  },
  {
    path:"task-detail/:id",
    component: TaskDetailComponent,
    canActivate: [AuthGuard],

  },
  {
    path:"new-task",
    component: NewTaskComponent,
    canActivate: [AuthGuard],

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
