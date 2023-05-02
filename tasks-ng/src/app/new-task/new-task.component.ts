import {Component, OnInit} from '@angular/core';
import {AllTask} from "../task";
import {AuthenticationService} from "../_service/authentication.service";
import {ClientService} from "../service/client-service";
import {Router} from "@angular/router";
import {TaskService} from "../_service/task.service";
import {Observable, of} from "rxjs";
import {Client} from "../client";

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit{
  allClients: Observable<Client[]> = of([]);
  allTask!: AllTask;
  constructor(private authenticationService: AuthenticationService,
              private clientService: ClientService,
              private router: Router,
              private taskService: TaskService) {

  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }

  ngOnInit(): void {
    this.allClients = this.getAllClients();

  }
  getAllClients(): Observable<Client[]> {
    return this.clientService
      .getAllClients()
  }
  addNewTask(){
    this.taskService.addNewTask(this.allTask).subscribe();
  }
}
