import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import {
  SubscriptionList,
  SubscriptionResponse,
} from 'src/app/interfaces/subscription.interface';
import { Theme, ThemeList } from 'src/app/interfaces/theme.interface';
import {
  UserDetail,
  UserDetailResponse,
} from 'src/app/interfaces/userDetail.interface';
import { SubscriptionsService } from 'src/app/services/api/subscriptions.service';
import { UserDetailService } from 'src/app/services/api/userDetail.service';
import { SessionService } from 'src/app/services/session.service';

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
    email: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(50),
        Validators.email,
      ],
    ],
  });

  constructor(
    private fb: FormBuilder,
    private subscriptionsService: SubscriptionsService,
    private sessionService: SessionService,
    private userDetailService: UserDetailService
  ) {}

  ngOnInit(): void {
    this.onArticleTab = false;
    this.onThemeTab = false;
    this.onUserTab = true;
    // auto remplissage du formulaire
    if (this.sessionService.sessionInformation != null) {
      this.userDetailForm.patchValue({
        username: this.sessionService.sessionInformation.username,
        email: this.sessionService.sessionInformation.email,
      });
    }
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

  saveUpdate(): void {
    let userDetailForm: UserDetail = {
      username: this.userDetailForm!.get('username')?.value || '',
      email: this.userDetailForm!.get('email')?.value || '',
    };
    this.userDetailService
      .updateUsernameAndEmail(userDetailForm)
      .subscribe((userDetailResponse: UserDetailResponse) => {
        localStorage.clear();
        location.reload();
      });
  }

  disconnect(): void {
    localStorage.clear();
    location.reload();
  }

  unSubscribeButton(id: number): void {
    const themeId = String(id);
    this.subscriptionsService
      .removeSubscription(themeId)
      .subscribe((subscriptionResponse: SubscriptionResponse) => {
        subscriptionResponse;
        location.reload();
      });
  }
}
