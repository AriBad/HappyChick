import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { AppConfigService } from '../app-config.service';
import { Poulailler, Poule, Saison } from '../model';

@Injectable({
  providedIn: 'root'
})
export class PoulaillerHttpService {
  
  poulaillerId : number;
  poulaillers : Array<Poulailler>;
  apiPath:string;
  
  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "poulailler/";
    this.load();
  }

  load() {
    this.poulaillerId = 1;
    this.http.get<Array<Poulailler>>(this.apiPath+"").subscribe(response => {
      this.poulaillers = response;
    });
  }

  getPoulaillerActuel():Observable<Poulailler> {
    return this.http.get<Poulailler>(this.apiPath+this.poulaillerId+"/poule");
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

  setPoulaillerActuel(id : number) : void {
    this.poulaillerId=id;
  }

  delete(id: number) {
    this.http.delete<void>(this.apiPath + id)
      .subscribe(resp => {
        this.load();
      });
  }

  saisonSuivante(saison:Saison) : Observable<void> {
    return this.http.put<void>(this.apiPath + this.poulaillerId + "/saison", saison);
  }
}
