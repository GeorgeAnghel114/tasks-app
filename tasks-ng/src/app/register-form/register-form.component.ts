import { Component } from '@angular/core';
import {Register} from "../register";
import { Clinet} from "../client";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  submitted:boolean=false;
  registerForm:Register= new Register("Email","User name","Password")
  client = {
    email:"",
    username:"",
    password:""
  }
  onSubmit(obj:any):void{
    console.log(obj);

  }

  newHero(){
    console.log(new Register("","",""))
  }
}
