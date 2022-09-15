import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'recap-utilisateur',
  templateUrl: './recap-utilisateur.component.html',
  styleUrls: ['./recap-utilisateur.component.scss']
})
export class RecapUtilisateurComponent implements OnInit {

  constructor(private authService:AuthService, private poulaillerService : PoulaillerHttpService) { }

  ngOnInit(): void {
  }

  getNom():string {
    return this.authService.utilisateur.login;
  }

  getSucces() : string {
    return "Pas encore prêt !";
  }

  deconnexion():void {
    this.authService.utilisateur=null;
    this.poulaillerService.deconnexion();
  }
}
