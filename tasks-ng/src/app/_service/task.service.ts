import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../task";

@Injectable({providedIn:'root'})
export class TaskService {
  taskUrl="http://localhost:8080/api/task/get-tasks";

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`}),
  };
  // public headers_object = new HttpHeaders().set("Authorization", "Bearer " + t);

  constructor(private http:HttpClient) {
  }

  getTasksOfUser():Observable<Task[]>{
    return this.http.get<Task[]>(this.taskUrl+"/"+localStorage.getItem('currentUser'),this.httpOptions)
  }
}
