import { Component, OnInit } from '@angular/core';
import { Player } from '../../Models/player';
import { PlayerService } from '../../services/player/player.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.sass']
})
export class PlayerDetailsComponent implements OnInit {
  id : number;
  player: Player;

  constructor(
    private playerService: PlayerService,
    private route: ActivatedRoute,
    private router: Router)
  {
    this.player = new Player();
  }

  ngOnInit() {
    this.route.params.subscribe(
      params => {
        this.id = +params['id'];
        this.playerService.findById(this.id).subscribe(
          data => {
            console.log(data)
            this.player = data;
          },
          err => {
            console.log("can not retrieve player with id " + this.id);
          }
        )
      },
      err => {
        console.log("can not retrieve id from route");
      }
    );
  }

  updatePlayer(){
    console.log(this.player);
    this.playerService.updatePlayer(this.player).subscribe(
      data => {
        console.log(data);
        this.back();
      },
      err => {
        console.log("can not update player with id " + this.id);
      }
    )
  }

  back(){
    this.router.navigate(['/players/']);
  }
}
