import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from "./app.component";
import { LoginComponent } from './login/login.component';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import { AuthGuard } from './security/auth.guard';
import { AppLayoutComponent } from './_layout/app-layout/app-layout.component';
import {RegisterComponent} from "./register/register.component";
import {ProfilchangeComponent} from "./pages/profilchange/profilchange.component";


const routes: Routes = [
  {
    path:'',component: AppLayoutComponent, canActivate: [AuthGuard],
    children: [
      { path: '',   redirectTo: '/dashboard', pathMatch: 'full' },
      {path:'dashboard',component:DashboardComponent},
      {path:'profilchange',component:ProfilchangeComponent}

    ]

  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
