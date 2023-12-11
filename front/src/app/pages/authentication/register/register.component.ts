import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subject, of } from 'rxjs';
import { RegisterRequest } from 'src/app/interfaces/registerRequest.interface';
import { AuthService } from 'src/app/services/authentication/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit, OnDestroy {
  loggedBool: Observable<boolean | null> = of(null);
  userConnected: boolean = false;
  private destroy = new Subject<void>();

  public onError = false;
  messageError: String = '';

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    username: [
      '',
      [Validators.required, Validators.min(3), Validators.max(20)],
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.min(8),
        Validators.max(40),
        this.validatePasswordCondition,
      ],
    ],
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.loggedBool = this.sessionService.$isLogged();
    this.loggedBool.subscribe((logged) => {
      if (logged) {
        this.userConnected = logged;
        this.router.navigate(['/postList']);
      } else {
        this.userConnected = false;
      }
    });
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (_: void) => this.router.navigate(['/login']),
      error: (error) => {
        this.onError = true;
        this.messageError = error.error.message;
      },
    });
  }

  public buttonPreviousTab(): void {
    this.router.navigate(['/home']);
  }

  private validatePasswordCondition(
    control: AbstractControl
  ): { [key: string]: boolean } | null {
    const value: string = control.value;
    const errors: { [key: string]: boolean } = {};

    const lowercaseRegex = /[a-z]/;
    const uppercaseRegex = /[A-Z]/;
    const digitRegex = /\d/;
    const specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;

    errors['lowercase'] = !lowercaseRegex.test(value);
    errors['uppercase'] = !uppercaseRegex.test(value);
    errors['digit'] = !digitRegex.test(value);
    errors['specialChar'] = !specialCharRegex.test(value);

    const isValid = !Object.values(errors).some((error) => error);

    if (!isValid) {
      return { passwordCondition: true, ...errors };
    }

    return null;
  }
}
