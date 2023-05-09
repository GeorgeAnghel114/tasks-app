import {Component} from '@angular/core';
import {Client} from "../client";
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  client: Client = {};
  message: string = "";
  showMessage: boolean = false;


  constructor(private _auth: AuthenticationService, private _router: Router) {
    if (this._auth.loggedIn) {
      this._router.navigate(['home']);
    }
  }

  // login(): void {
  //   if (this.client.username != undefined && this.client.password != undefined) {
  //     this._auth.loginRequest(this.client).subscribe(response => {
  //       this._auth.saveIntoLocalStorage(response.username, response.token);
  //       console.log(response)
  //       if (response) {
  //         this._router.navigate(['home'])
  //       } else {
  //         alert("wrong username or password")
  //       }
  //     }, error => {
  //       console.log(error)
  //       alert("Wrong credentials, try again or make an account!")
  //     })
  //   }


  // }
  resetForm(myForm: NgForm) {
    this.showMessage = false;
    myForm.resetForm();
  }

  login(): void {
    if (this.client.username != undefined && this.client.password != undefined) {
      this._auth.loginRequest(this.client).subscribe({
        error:(error) => {
          this.message=error.error;
          this.showMessage=true;
          console.log(this.message)
        },
        next:(response)=>{
          this._auth.saveIntoLocalStorage(response.username, response.token);
          if (response) {
            this._router.navigate(['home'])
          }
        }
      })
    }
  }
}
