import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AllTask, Task} from "../task";

@Injectable({providedIn: 'root'})
export class TaskService {
  taskUrl: string = "http://localhost:8080/api/task/tasks";
  allTaskUrl:string = "http://localhost:8080/api/task/all-tasks";
  taskDetailUrl:string = "http://localhost:8080/api/task"

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

  getTask(id:number):Observable<AllTask>{
    const url:string = `${this.taskDetailUrl}/${id}`;
    return this.http.get<AllTask>(url,this.httpOptions);
  }
}
