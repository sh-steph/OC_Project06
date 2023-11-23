import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { SubscriptionList, SubscriptionResponse } from 'src/app/interfaces/subscription.interface';
import { Theme, ThemeList } from 'src/app/interfaces/theme.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SubscriptionsService } from 'src/app/services/api/subscriptions.service';
import { ThemesService } from 'src/app/services/api/themes.service';
import { SessionService } from 'src/app/services/session.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss'],
})
export class UserDetailComponent implements OnInit, OnDestroy {
  onThemeTab: boolean = false;
  onArticleTab: boolean = false;
  onUserTab: boolean = false;

  public getAllThemes: Observable<ThemeList | null> = of(null);
  public getAllSubscriptions: Observable<SubscriptionList | null> = of(null);
  private destroy = new Subject<void>();

  subscribedList: ThemeList = { themes: [] };

  userDetailForm = this.fb.group({
    username: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.min(3), Validators.max(50)]],
  });

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private themesService: ThemesService,
    private subscriptionsService: SubscriptionsService
  ) {}

  ngOnInit(): void {
    this.onArticleTab = false;
    this.onThemeTab = false;
    this.onUserTab = true;
    this.mySubscribedList();
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  mySubscribedList(): void {
    this.getAllSubscriptions =
      this.subscriptionsService.getAllSubscriptionFromUser();
    this.getAllSubscriptions
      .pipe(takeUntil(this.destroy))
      .subscribe((subscriptionData) => {
        if (subscriptionData) {
          const themes: Theme[] = subscriptionData.subscriptions.map(
            (subscription) => subscription.theme
          );
          this.subscribedList = { themes: themes };
        }
      });
  }

  sortThemesByAlphabetical(themes: Theme[]): Theme[] {
    return themes.slice().sort((a, b) => a.title.localeCompare(b.title));
  }

  saveUpdate(): void {}

  disconnect(): void {
    this.router.navigate(['home']);
  }

  unSubscribeButton(id: number): void {
    const themeId = String(id);
    this.subscriptionsService
    .removeSubscription(themeId)
    .subscribe((subscriptionResponse: SubscriptionResponse) => subscriptionResponse)
  }
}
