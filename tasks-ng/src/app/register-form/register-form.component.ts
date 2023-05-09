import {Component} from '@angular/core';
import {Client} from "../client";
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  message: String = "";
  showMessage: boolean = false;
  client: Client = {};

  constructor(private _auth: AuthenticationService,
              private _router: Router) {
  }

  register() {
    this._auth.register(this.client).subscribe(
      {
        error: (error) => {
          this.message = error;
          this.showMessage = true;
        },
        next: () => {
          this._router.navigate(['login'])
        }
      }
    )
  }

  resetForm(myForm: NgForm) {
    this.showMessage = false;
    myForm.resetForm();
  }
}
