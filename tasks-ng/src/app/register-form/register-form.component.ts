import {Component} from '@angular/core';
import {Client, RegisterUser} from "../client";
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import { NgForm} from "@angular/forms";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  submitted: boolean = false;
  message:String = "";
  showMessage:boolean = false;
  client: Client = {};

  constructor(private _auth:AuthenticationService,
              private _router:Router,) {
  }

  register() {
    this._auth.register(this.client).subscribe(
      {
        next:()=>{
          this._router.navigate(['home'])
        },
        error:(error)=>{
          this.message=error;
          this.showMessage=true;
        }
      }
    )

    //   response =>{
    //   console.log(response)
    //   if(response){
    //     this._router.navigate(['login'])
    //     alert("Registere completed")
    //   }else{
    //     alert("Something went wrong. Please try again!")
    //   }
    // })
  }

  resetForm(myForm: NgForm) {
    myForm.resetForm();
  }
}
