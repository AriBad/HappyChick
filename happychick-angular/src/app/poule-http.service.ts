import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';
import { Poulailler, Poule } from './model';
import { PoulaillerHttpService } from './poulailler/poulailler-http.service';

@Injectable({
  providedIn: 'root'
})
export class PouleHttpService {

  poules: Array<Poule> = new Array<Poule>();

  poule: Poule = new Poule;

  apiPath: string;
  poulailler: Poulailler;

  constructor(private http: HttpClient, private appConfig: AppConfigService, private poulaillerService: PoulaillerHttpService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "poule/";
    this.poulailler=poulaillerService.getPoulaillerActuel();
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

  getPoulesVivantes() {
    this.poulailler.listePoules.filter(poule=> poule.causeMort==null);
  }

  getPoulesByPoulailler(): Array<Poule> {
    return this.poulailler.listePoules;
  }
  getPouleByTemperament(temperament: String): Array<Poule> {
    return this.poulailler.listePoules.filter(poule => poule.temperament == temperament);
  }

  getPoulesCouveuseByTemperament(temperament: String): Array<Poule> {
    return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.oeufsCouves!==0);
  }

  getPoulesMaternageByTemperament(temperament: String): Array<Poule> {
    return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.maternage!==0);
  }
  getBonheurMoyen(temperament: String): number{
   let bonheur : number = 0 ; 
   let  cpt : number =0 ;
   this.getPouleByTemperament(temperament).forEach(p => bonheur = bonheur+p.bonheur && cpt++);

  return bonheur/cpt; 

  }
}

