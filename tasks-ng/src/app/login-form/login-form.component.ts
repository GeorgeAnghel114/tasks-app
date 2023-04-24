import { Component } from '@angular/core';
import {Client} from "../client";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  client:Client = new Client();


  onSubmit(){
    console.log("you logged in")
  }
}
