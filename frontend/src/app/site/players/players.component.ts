import { Component, OnInit } from '@angular/core';
import { Player } from '../../Models/player';
import { PlayerService } from 'src/app/services/player/player.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.sass']
})
export class PlayersComponent implements OnInit {

  players: Player[];
  newPlayer: Player;
  errorMessage: string;
  serverError: string[];
  hideForm: boolean;

  constructor(
    private playerService: PlayerService,
    private router: Router
  ) {
    this.newPlayer = new Player();
  }

  ngOnInit() {
    this.getAll();
    this.hideForm = true;
  }

  getAll() {
    this.playerService.findAll().subscribe(
      data => {
        this.players = data;
        console.log(data);
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
      }
    );
  }

  getOnePlayer() {
    this.playerService.findById(2).subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
      }
    );
  }

  addAPlayer(){
    this.errorMessage = null;
    this.playerService.createPlayer(this.newPlayer).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
      }
    );
  }

  removePlayer(id: number){
    this.errorMessage = null;
    this.playerService.deletePlayer(id).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
      }
    );
  }

  goTo(id: number){
    this.router.navigate(['/player/', id]);
  }

}
