import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { MatSidenav } from '@angular/material/sidenav';
import { AuthService } from 'src/app/services/authentication/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  userConnected: boolean = false;
  onPostTab: boolean = false;
  onThemeTab: boolean = false;
  onUserDetailTab: boolean = false;
  private destroy = new Subject<void>();

  isCollapse = false;
  sidenav?: MatSidenav;
  sidenavWidth = 200;
  visibleSidebar2: boolean = false;

  constructor(
    private router: Router,
    private changeDetectorRef: ChangeDetectorRef,
    private authservice: AuthService
  ) {}

  ngOnInit(): void {
    this.userConnected = this.authservice.isLoggedIn();
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
    if (!this.userConnected) {
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
    // Ouvrir ou fermer le sidenav en fonction de l'état isCollapse
    if (this.isCollapse && this.sidenav) {
      this.sidenav.open();
    } else {
      this.sidenav?.close();
    }
  }
}
