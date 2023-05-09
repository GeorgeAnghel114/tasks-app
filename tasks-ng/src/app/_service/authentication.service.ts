import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Client, LoggedUser} from "../client";
import {catchError, map, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(public http: HttpClient) {

  }
  register(client:Client):Observable<boolean>{
    return this.http.post<Client>("http://localhost:8080/api/client/register",client,{
      observe:'response'
    })
      .pipe(
        map((response:HttpResponse<any>)=>{
          console.log(response)
          return true;
        }),
        catchError((error: HttpErrorResponse)=>{
          console.log(error)
          return throwError(error.error)
        })
      )
  }

  saveIntoLocalStorage(username: string, token: string): void {
    localStorage.setItem('currentUser', username);
    localStorage.setItem('token', token);
  }

  loginRequest(client: Client) {
    return this.http.post<LoggedUser>("http://localhost:8080/api/client/login", client)
  }

  logout() {
    localStorage.removeItem('currentUser')
  }

  public get loggedIn(): boolean {
    return (localStorage.getItem('currentUser') !== null);
  }



}
