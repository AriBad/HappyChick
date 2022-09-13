import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { AppConfigService } from '../app-config.service';
import { Poulailler, Poule, Saison } from '../model';

@Injectable({
  providedIn: 'root'
})
export class PoulaillerHttpService {
  
  poulailler : Poulailler;
  poulaillers : Array<Poulailler>;
  apiPath:string;
  
  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "poulailler/";
    
    this.load();
  }

  load() {
    this.http.get<Array<Poulailler>>(this.apiPath+"").subscribe(response => {
      this.poulaillers = response;
    });
    this.http.get<Poulailler>(this.apiPath+"1").subscribe(response => {
      this.poulailler = response;
    });
  }

  setPoulaillerActuel(id:number):void {
    this.poulaillers.forEach((value) => {
      if(value.id == id) {
        this.poulailler=value;
      }
    });
  }

  getPoulaillerActuel():Poulailler {
    return this.poulailler;
  }

  getAll(): Array<Poulailler> {
    return this.poulaillers;
  }

  findById(id: number): Observable<Poulailler> {
    return this.http.get<Poulailler>(this.apiPath+id);
  }

  save(poulailler: Poulailler) {
    if(poulailler.id) { // modification
      this.http.put<Poulailler>(this.apiPath + poulailler.id, poulailler)
        .subscribe(resp => {
          this.load();
        });
    } else { // création
      this.http.post<Poulailler>(this.apiPath, poulailler)
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

  saisonSuivante(saison:Saison) {
    this.http.put<void>(this.apiPath + this.poulailler.id + "/saison", saison).subscribe(resp => {
      this.load();
    });
  }

  getPoulesLibres() {
    this.poulailler.listePoules.filter(poule=> poule.causeMort==null && poule.etat=="Liberte");
  }

}
