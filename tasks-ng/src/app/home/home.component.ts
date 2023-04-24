import {Component, OnInit} from '@angular/core';
import {Route, Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  constructor(private router: Router, private authenticationService:AuthenticationService) {
  }
  ngOnInit() {
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['']);
  }
}