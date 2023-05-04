import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../_service/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {AllTask} from "../task";
import {TaskService} from "../_service/task.service";
import {ClientService} from "../service/client-service";
import {Client} from "../client";
import {Observable, of} from "rxjs";


@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  // @Input() allTask?: AllTask;
  allTask?: AllTask;
  allClients: Observable<Client[]> = of([]);
  newTask!: AllTask;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private taskService: TaskService,
              private authenticationService: AuthenticationService,
              private location: Location,
              private clientService: ClientService) {
  }

  ngOnInit() {
    this.getTask();
    this.allClients = this.getAllClients();
  }


  redirect() {
    this.router.navigate(['home'])
  }

  getTask() {
    const id: number = Number(this.route.snapshot.paramMap.get('id'))
    this.taskService.getTask(id).subscribe((allTask) => {
      this.allTask = allTask;
      this.newTask = {...allTask};
    })
  }

  getAllClients(): Observable<Client[]> {
    return this.clientService
      .getAllClients()
  }

  updateTask() {
    const id: number = Number(this.route.snapshot.paramMap.get('id'))
    this.taskService.updateTask(this.allTask, id).subscribe();
  }

}
