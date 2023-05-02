import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {AllTask} from "../task";
import {TaskService} from "../_service/task.service";
import { ActivatedRoute } from '@angular/router';
import {ClientService} from "../service/client-service";
import {Client} from "../client";
import {Observable, of} from "rxjs";
import {AllTasksComponent} from "../all-tasks/all-tasks.component";


@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit{
  // @Input() allTask?: AllTask;
  allTask?:AllTask;
  allClients:Observable<Client[]>=of([]);


  constructor(private route: ActivatedRoute,
              private router: Router,
              private taskService: TaskService,
              private authenticationService: AuthenticationService,
              private location: Location,
              private clientService: ClientService) {
  }

  ngOnInit(){
    this.getTask();
    this.allClients=this.getAllClients();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }

  getTask(){
    const id: number = Number(this.route.snapshot.paramMap.get('id'))
    this.taskService.getTask(id).subscribe((allTask)=>this.allTask=allTask)
  }
  getAllClients():Observable<Client[]>{
    return this.clientService
      .getAllClients()
  }

  updateTask(){
    const id: number = Number(this.route.snapshot.paramMap.get('id'))
    this.taskService.updateTask(this.allTask,id).subscribe();
    this.onSubmit();
  }

  onSubmit(){
    console.log(this.allTask);
  }
}
