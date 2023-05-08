import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {AllTask, SearchForm, Task} from "../task";

@Injectable({providedIn: 'root'})
export class TaskService {
  taskUrl: string = "http://localhost:8080/api/task/tasks";
  allTaskUrl: string = "http://localhost:8080/api/task/all-tasks";
  taskDetailUrl: string = "http://localhost:8080/api/task";
  updateTaskUrl: string = "http://localhost:8080/api/task/update-task";
  addNewTaskUrl: string = "http://localhost:8080/api/task/add-task";
  searchFormUrl: string = "http://localhost:8080/api/task/search";
  deleteTask: string = "http://localhost:8080/api/task/delete-task/";
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

  getAllTasks(): Observable<AllTask[]> {
    return this.http.get<AllTask[]>(this.allTaskUrl, this.httpOptions);
  }

  getTask(id: number): Observable<AllTask> {
    const url: string = `${this.taskDetailUrl}/${id}`;
    return this.http.get<AllTask>(url, this.httpOptions);
  }

  updateTask(allTask: AllTask | undefined, id: number): Observable<any> {
    const url: string = `${this.updateTaskUrl}/${id}`;
    return this.http.put(url,allTask, this.httpOptions);
  }

  addNewTask(allTask:AllTask | undefined):Observable<AllTask>{
    const url: string = `${this.addNewTaskUrl}`
    return this.http.post<AllTask>(url,allTask,this.httpOptions);
  }

  searchTasks(searchForm:SearchForm | undefined):Observable<AllTask[]>{
    const url: string = this.searchFormUrl;
    if(!searchForm){
      return of([]);
    }
    return this.http.post<AllTask[]>(url,searchForm,this.httpOptions)
  }

  deleteTaskById(id:number):Observable<AllTask>{
    const url: string = this.deleteTask
     return this.http.delete<any>(`${url}`+`${id}`,this.httpOptions);
  }
}
