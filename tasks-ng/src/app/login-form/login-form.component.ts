import { Component } from '@angular/core';
// import {Client} from "../client";
import {AuthenticationService} from "../_service/authentication.service";
import { Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  // client:Client = new Client();
  username:string = '';
  password:string = '';

  constructor(private _auth: AuthenticationService, private _router: Router) {
    if (this._auth.loggedIn) {
      this._router.navigate(['home']);
    }
  }

  login():void{
    if(this.username!='' && this.password!=''){
      if(this._auth.login(this.username,this.password)){
        this._router.navigate(['home'])
      }
      else{
        alert("wrong username or password")
      }
    }
  }

  onSubmit(){
    console.log("you logged in")
  }
}
