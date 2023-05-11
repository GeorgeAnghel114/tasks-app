import {Component, OnInit} from '@angular/core';
import {AllTask} from "../task";
import {AuthenticationService} from "../_service/authentication.service";
import {ClientService} from "../service/client-service";
import {Router} from "@angular/router";
import {TaskService} from "../_service/task.service";
import {Observable, of} from "rxjs";
import {Client} from "../client";
import {FormGroup, NgForm} from "@angular/forms";


@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {
  allClients: Observable<Client[]> = of([]);
  allTask?: AllTask = {};
  clientEmail: string | undefined;
  subject: string | undefined;
  status: string | undefined;
  duedate: string | undefined;

  constructor(private authenticationService: AuthenticationService,
              private clientService: ClientService,
              private router: Router,
              private taskService: TaskService) {

  }


  resetForm(form: FormGroup | NgForm) {
    form.reset();
  }

  ngOnInit(): void {
    this.allClients = this.getAllClients();

  }

  getAllClients(): Observable<Client[]> {
    return this.clientService
      .getAllClients()
  }

  addNewTask() {
    this.allTask!.clientEmail = this.clientEmail;
    this.allTask!.subject = this.subject;
    this.allTask!.status = this.status;
    this.allTask!.duedate = this.duedate;
    this.taskService.addNewTask(this.allTask).subscribe();
    alert("Task added!")
    this.router.navigate(['/home'])
  }
}
