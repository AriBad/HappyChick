import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';
import { Poulailler, Poule } from './model';

@Injectable({
  providedIn: 'root'
})
export class PouleHttpService {

  poules: Array<Poule> = new Array<Poule>();
  poulaillers: Array<Poulailler> = new Array<Poulailler>();

  apiPath: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "poule/";
    this.load();
  }

  load() {
    this.http.get<Array<Poule>>(this.apiPath+'full').subscribe(response => {
      this.poules = response;
    });
  }

  getAll(): Array<Poule> {
    return this.poules;
  }
 /* findAllbyPoulailler(): Array<Poule> {
    return this.poules;
  }
*/
  findById(id: number): Observable<Poule> {
    return this.http.get<Poule>(this.apiPath+id);
  }

  save(poule: Poule) {
    if(poule.id) { // modification
      this.http.put<Poule>(this.apiPath + poule.id, poule)
        .subscribe(resp => {
          this.load();
        });
    } else { // création
      this.http.post<Poule>(this.apiPath, poule)
        .subscribe(resp => {
          this.load();
        });
    }
  }

  delete(id: number) {
    this.http.delete<void>(this.apiPath + id)
      .subscribe(resp => {
        this.load();
      });
  }
}
