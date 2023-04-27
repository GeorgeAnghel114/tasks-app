import {Component} from '@angular/core';
import {Client} from "../client";
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  client: Client = {};


  constructor(private _auth: AuthenticationService, private _router: Router) {
    if (this._auth.loggedIn) {
      this._router.navigate(['home']);
    }
  }

  login(): void {
    if (this.client.username != undefined && this.client.password != undefined) {
      this._auth.loginRequest(this.client).subscribe(response => {
        this._auth.login(response.username);

        console.log(response)
        if (response) {
          this._router.navigate(['home'])
        } else {
          alert("wrong username or password")
        }
      }, error => {
        console.log(error)
        alert("Wrong credentials! Make an account!")
        this._router.navigate(['register'])

      })
    }
  }

  onSubmit() {
    console.log("you logged in")
  }
}
