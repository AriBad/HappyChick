import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../model';
import { AppConfigService } from '../app-config.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurHttpService {
  utilisateurs: Array<Utilisateur> = new Array<Utilisateur>();

  apiPath: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "utilisateur/";
  }

  load() {
    this.http.get<Array<Utilisateur>>(this.apiPath).subscribe(response => {
      this.utilisateurs = response;
    });
  }

  findAll(): Array<Utilisateur> {
    return this.utilisateurs;
  }

  findById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(this.apiPath+id);
  }

  save(utilisateur: Utilisateur) {
    if(utilisateur.id) { // modification
      this.http.put<Utilisateur>(this.apiPath + utilisateur.id, utilisateur)
        .subscribe(resp => {
          this.load();
        });
    } else { // création
      this.http.post<Utilisateur>(this.apiPath, utilisateur)
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

  login(identifiant: string, motDePasse: string): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(this.apiPath+"login", {
      "identifiant": identifiant,
      "motDePasse": motDePasse
    });
  }
}
