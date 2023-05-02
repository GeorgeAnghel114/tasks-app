import {Component, OnInit} from '@angular/core';
import {AllTask} from "../task";
import {TaskService} from "../_service/task.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";

@Component({
  selector: 'app-all-tasks',
  templateUrl: './all-tasks.component.html',
  styleUrls: ['./all-tasks.component.css']
})
export class AllTasksComponent implements OnInit{
  allTask:AllTask[]=[];

  constructor(private taskService: TaskService,
              private router:Router,
              private authenticationService: AuthenticationService) {
  }
  ngOnInit() {
    this.getAllTasks();

  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }
  getAllTasks():void{
    this.taskService.getAllTasks().subscribe(allTask=>this.allTask=allTask)

  }

}
