import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { LoginComponent } from './pages/authentication/login/login.component';
import { RegisterComponent } from './pages/authentication/register/register.component';
import { PostListComponent } from './pages/post-list/post-list.component';
import { ThemeListComponent } from './pages/theme-list/theme-list.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  // {
  //   path: '',
  //   canActivate: [UnauthGuard],
  //   loadChildren: () =>
  //     import('./features/auth/auth.module').then((m) => m.AuthModule),
  // },
  {
    path: '',
    canActivate: [UnauthGuard],
    component: LoginComponent,
  },
  {
    path: 'home',
    canActivate: [AuthGuard],
    component: HomeComponent,
  },
  {
    path: 'login',
    canActivate: [UnauthGuard],
    component: LoginComponent,
  },
  {
    path: 'register',
    canActivate: [UnauthGuard],
    component: RegisterComponent,
  },
  {
    path: 'postList',
    canActivate: [AuthGuard],
    component: PostListComponent,
  },
  {
    path: 'themeList',
    canActivate: [AuthGuard],
    component: ThemeListComponent,
  },
  {
    path: 'user-detail',
    canActivate: [AuthGuard],
    component: UserDetailComponent,
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
