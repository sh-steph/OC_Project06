import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/authentication/auth.service';

@Injectable({ providedIn: 'root' })
export class UnauthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  public canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/postList']);
      return false;
    } else {
      return true;
    }
  }
}
