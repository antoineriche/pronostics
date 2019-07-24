import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from '../../Models/player';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private playersUrl: string;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.playersUrl = 'http://localhost:8087/pronos/players/';
  }

  public findAll(): Observable<Player[]> {
    return this.http.get<Player[]>(this.playersUrl);
  }

  public findById(id: any): Observable<Player> {
    var url = this.playersUrl+id;
    return this.http.get<Player>(url);
  }

  public deletePlayer(id: any){
    return this.http.delete(this.playersUrl+id);
  }

  public createPlayer(postPlayer: Player): Observable<Player> {
    postPlayer.subscribeDate = new Date();
    return this.http.post<Player>(this.playersUrl, postPlayer);
  }

  public updatePlayer(postPlayer: Player): Observable<Player> {
    var url = this.playersUrl+postPlayer.id;
    return this.http.put<Player>(url, postPlayer);
  }
}
