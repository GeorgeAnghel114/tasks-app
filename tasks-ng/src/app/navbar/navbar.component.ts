import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }

  protected readonly localStorage = localStorage;
}
