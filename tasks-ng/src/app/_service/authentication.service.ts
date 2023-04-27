import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client, LoggedUser} from "../client";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(public http: HttpClient) {

  }
  register(client:Client){
    return this.http.post<Client>("http://localhost:8080/api/client/register",client)
  }

  login(username: string,token:string): void {
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
