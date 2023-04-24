import { Component } from '@angular/core';
import {Register} from "../register";
import {Client} from "../client";
import {ClientService} from "../service/client-service";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  submitted:boolean=false;
  registerForm:Register= new Register("Email","User name","Password")
  client: Client = new Client();

  constructor(private clientService:ClientService) {
  }

  onSubmit(){
    this.clientService.save(this.client).subscribe();
  }



}
