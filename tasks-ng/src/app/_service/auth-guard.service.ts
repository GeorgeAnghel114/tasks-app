import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from "./authentication.service";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(private _router: Router, public authenticationService: AuthenticationService) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authenticationService.loggedIn) {
      return true;
    }
    this._router.navigate(['/login']).then();
    return false;
  }
}
