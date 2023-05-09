import {Component} from '@angular/core';
import {Client, RegisterUser} from "../client";
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import { FormGroup,NgForm} from "@angular/forms";
import {throwError} from "rxjs";

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

  constructor(private _auth:AuthenticationService, private _router:Router) {
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
          console.log(this.message)
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

  resetForm(form: FormGroup | NgForm) {
    form.reset();
  }
}
