import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";
import { TaskService} from "../_service/task.service";
import {Task} from "../task";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // username:string = localStorage.getItem('value');
  tasks:Task[]=[]

  constructor(private taskService: TaskService,private router: Router, private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.getTasksOfUser();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }

  getTasksOfUser():void{
    this.taskService.getTasksOfUser().subscribe(tasks=>this.tasks=tasks)
  }

  protected readonly localStorage = localStorage;
}
