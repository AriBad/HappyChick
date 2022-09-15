import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utilisateur } from './model';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  utilisateur: Utilisateur;

  constructor(private http: HttpClient, private appConfig: AppConfigService) { }

  login(identifiant: string, motDePasse: string): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(this.appConfig.apiBackEndUrl + "utilisateur/login", {
      "identifiant": identifiant,
      "motDePasse": motDePasse
    });
  }
}
