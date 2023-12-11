import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, of } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  loggedBool: Observable<boolean | null> = of(null);
  userConnected: boolean = false;
  private destroy = new Subject<void>();

  constructor(private router: Router) {}

  ngOnInit(): void {}

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  login() {
    this.router.navigate(['/login']);
  }

  register() {
    this.router.navigate(['/register']);
  }
}
