import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  loggedBool: Observable<boolean | null> = of(null);
  userConnected: boolean = false;
  private destroy = new Subject<void>();

  constructor(
    private router: Router,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.loggedBool = this.sessionService.$isLogged();
    this.loggedBool
    .pipe(takeUntil(this.destroy))
    .subscribe((logged) => {
        // wait to get data from subscribe
        if (logged) {
          this.userConnected = logged;
          this.router.navigate(['/postList']);
        } else {
          this.userConnected = false;
        }
      });
  }

  ngOnDestroy() {
    this.destroy.next()
    this.destroy.unsubscribe
  }

  login() {
    this.router.navigate(['/login']);
  }

  register() {
    this.router.navigate(['/register']);
  }
}
