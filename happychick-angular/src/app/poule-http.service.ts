import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';
import { Poulailler, Poule } from './model';
import { PoulaillerHttpService } from './poulailler/poulailler-http.service';
import { PouleComponent } from './poule/poule.component';


@Injectable({
  providedIn: 'root'
})
export class PouleHttpService {

  poules: Array<Poule> = new Array<Poule>();
  poulaillerId : number;
  poule: Poule = new Poule;

  apiPath: string;
  poulailler: Poulailler;
  constructor(private http: HttpClient, private appConfig: AppConfigService, private poulaillerService: PoulaillerHttpService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "poule/";
    this.load();
  }

  load() {
    this.http.get<Array<Poule>>(this.apiPath).subscribe(response => {
      this.poules = response;
    });
  }

  getAll(): Array<Poule> {
    return this.poules;
  }

  findById(id: number): Observable<Poule> {
    return this.http.get<Poule>(this.apiPath+id);
  }

  getPoulesMortes(causemort: String) {
    this.poulailler.listePoules.filter(poule=> poule.causeMort==causemort);
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
  
getPoulesByPoulailler(): Array<Poule> {
  return this.poulailler.listePoules;
}
  getPoulesVivantes() {
    this.poulailler.listePoules.filter(poule=> poule.causeMort==null);
  }

  getPouleByTemperament(temperament: String): Array<Poule> {
    return this.poulailler.listePoules.filter(poule => poule.temperament == temperament);
  }
}

