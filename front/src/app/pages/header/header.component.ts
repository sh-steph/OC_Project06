import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  loggedBool: Observable<boolean | null> = of(null);
  userConnected: boolean = false;
  onPostTab: boolean = false;
  onThemeTab: boolean = false;
  onUserDetailTab: boolean = false;
  private destroy = new Subject<void>();

  isCollapse = false;

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loggedBool = this.sessionService.$isLogged();
    this.loggedBool.pipe(takeUntil(this.destroy)).subscribe((logged) => {
      // wait to get data from subscribe
      if (logged) {
        this.userConnected = logged;
      } else {
        this.userConnected = false;
      }
    });
    this.setTabsBasedOnUrl();
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  private setTabsBasedOnUrl(): void {
    const currentUrl = this.router.url;
    this.onPostTab = currentUrl.includes('postList');
    this.onThemeTab = currentUrl.includes('themeList');
    this.onUserDetailTab = currentUrl.includes('userDetail');
    this.changeDetectorRef.detectChanges();
  }

  public buttonMDD(): void {
    if (this.loggedBool) {
      this.router.navigate(['/register']);
    } else {
      this.router.navigate(['/postList']);
    }
  }

  postTab(): void {
    this.onPostTab = true;
    this.onThemeTab = false;
    this.onUserDetailTab = false;
    this.router.navigate(['/postList']);
  }

  themeTab(): void {
    this.onPostTab = false;
    this.onThemeTab = true;
    this.onUserDetailTab = false;
    this.router.navigate(['/themeList']);
  }

  userDetailTab(): void {
    this.onPostTab = false;
    this.onThemeTab = false;
    this.onUserDetailTab = true;
    this.router.navigate(['/userDetail']);
  }

  collapseMenu(): void {
    this.isCollapse = !this.isCollapse;
  }
}
