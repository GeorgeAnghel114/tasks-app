import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AllTask, Task} from "../task";

@Injectable({providedIn: 'root'})
export class TaskService {
  taskUrl = "http://localhost:8080/api/task/tasks";
  allTaskUrl:string = "http://localhost:8080/api/task/all-tasks";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    }),
  };

  constructor(private http: HttpClient) {
  }

  getTasksOfUser(): Observable<Task[]> {
    return this.http.get<Task[]>(this.taskUrl + "/" + localStorage.getItem('currentUser'), this.httpOptions)
  }

  getAllTasks():Observable<AllTask[]>{
    return this.http.get<AllTask[]>(this.allTaskUrl,this.httpOptions);
  }
}
