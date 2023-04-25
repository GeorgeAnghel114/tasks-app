import { Injectable } from '@angular/core';
import {Client} from "../client";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  client:Client = new Client("","");
  login(username:string,password:string):boolean{
    if(username=='admin' && password=='admin'){
      localStorage.setItem('currentUser','loggedin');
      return true;
    }
    return false;
  }

  logout(){
    localStorage.removeItem('currentUser')
  }

  public get loggedIn():boolean{
    return (localStorage.getItem('currentUser')!==null);
  }
  constructor() { }
}
