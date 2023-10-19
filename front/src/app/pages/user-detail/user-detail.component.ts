import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss'],
})
export class UserDetailComponent implements OnInit {
  public user: User | undefined;

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private matSnackBar: MatSnackBar,
    private userService: UserService
  ) {}

  public ngOnInit(): void {
    this.userService
      .getById(this.sessionService.sessionInformation!.id.toString())
      .subscribe((user: User) => (this.user = user));
  }

  public back(): void {
    window.history.back();
  }

  public delete(): void {
    this.userService
      .delete(this.sessionService.sessionInformation!.id.toString())
      .subscribe((_) => {
        this.matSnackBar.open('Your account has been deleted !', 'Close', {
          duration: 3000,
        });
        this.sessionService.logOut();
        this.router.navigate(['/']);
      });
  }
}
