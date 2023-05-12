import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {AllTask, SearchForm} from "../task";
import {TaskService} from "../_service/task.service";
import {Client} from "../client";
import {ClientService} from "../service/client-service";
import {FormGroup, NgForm} from "@angular/forms";

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {
  searchId = null;
  searchSubject = null;
  searchDuedate = null;
  searchStatus = null;
  // searchForm: SearchForm = {};
  clients?: Observable<Client[]>
  allTask?: AllTask[] = [];


  constructor(private taskService: TaskService,
              private clientService: ClientService) {
  }

  resetForm(form: FormGroup | NgForm) {
    form.reset();
  }


  ngOnInit(): void {
    this.clients = this.clientService.getAllClients();
  }

  searchTasks() {
    const params: SearchForm = {
      clientId: this.searchId!,
      subject: this.searchSubject!,
      duedate: this.searchDuedate!,
      status:this.searchStatus!,
    }
    console.log(params.clientId)
    this.taskService.searchTasks(params).subscribe((res) => {
      this.allTask = res
      if (this.allTask.length === 0) {
        alert("0 results for this searching criteria!")
      }
    });

  }
}
