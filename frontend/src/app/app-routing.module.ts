import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './site/home/home.component';
import { AuthGuard } from './services/auth/auth.guard';
import { LoginComponent } from './site/login/login.component';
import { PlayersComponent } from './site/players/players.component';
import { PlayerDetailsComponent } from './site/player-details/player-details.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'players', component: PlayersComponent },
  { path: 'player/:id', component: PlayerDetailsComponent },
  { path: 'player', redirectTo:'/players' },
  { path: '', component : HomeComponent,canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
