import {Component, Input} from '@angular/core';
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {AllTask} from "../task";
import {TaskService} from "../_service/task.service";
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent {
  @Input() allTask?: AllTask;
  // allTask?:AllTask;


  constructor(private route: ActivatedRoute,
              private router: Router,
              private taskService: TaskService,
              private authenticationService: AuthenticationService,
              private location: Location) {
  }

  ngOnInit(){
    this.getTask();
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }

  getTask(){
    const id: number = Number(this.route.snapshot.paramMap.get('id'))
    this.taskService.getTask(id).subscribe((allTask)=>this.allTask=allTask)
  }
}
