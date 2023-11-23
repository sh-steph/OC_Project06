import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import { SubscriptionResponse } from 'src/app/interfaces/subscription.interface';
import { Theme, ThemeList } from 'src/app/interfaces/theme.interface';
import { SubscriptionsService } from 'src/app/services/api/subscriptions.service';
import { ThemesService } from 'src/app/services/api/themes.service';

@Component({
  selector: 'app-theme-list',
  templateUrl: './theme-list.component.html',
  styleUrls: ['./theme-list.component.scss'],
})
export class ThemeListComponent implements OnInit, OnDestroy {
  onThemeTab: boolean = false;
  onArticleTab: boolean = false;
  onUserTab: boolean = false;

  public getAllThemes: Observable<ThemeList | null> = of(null);
  private destroy = new Subject<void>();

  themeList: ThemeList = { themes: [] };

  constructor(private themesService: ThemesService, private subscriptionsService: SubscriptionsService,private router: Router) {}

  ngOnInit(): void {
    this.onArticleTab = false;
    this.onThemeTab = true;
    this.onUserTab = false;
    this.getAllThemes = this.themesService.getAllTheme();
    this.getAllThemes.pipe(takeUntil(this.destroy)).subscribe((themesData) => {
      // wait to get data from subscribe
      if (themesData) {
        this.themeList = themesData;
      }
    });
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  sortThemesByAlphabetical(themes: Theme[]): Theme[] {
    return themes.slice().sort((a, b) => a.title.localeCompare(b.title));
  }

  subscribeButton(id: number): void {
    const themeId = String(id);
    this.subscriptionsService
    .addSubscription(themeId)
    .subscribe((subscriptionResponse: SubscriptionResponse) => subscriptionResponse)
  }
}
