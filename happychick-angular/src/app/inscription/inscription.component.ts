import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Utilisateur } from '../model';
import { UtilisateurHttpService } from '../utilisateur/utilisateur-http.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  login:string;
  motDePasse:string;
  constructor(private utilisateurService : UtilisateurHttpService, private router: Router) { 

  }

  ngOnInit(): void {
  }

  validerInscription() : void {
    let utilisateur:Utilisateur = new Utilisateur(this.login,this.motDePasse);
    this.utilisateurService.save(utilisateur);
    this.router.navigate(["/home"]);
  }



  
}
