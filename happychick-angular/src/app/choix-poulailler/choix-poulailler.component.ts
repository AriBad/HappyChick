import { Component, OnInit } from '@angular/core';
import { Poulailler } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'choix-poulailler',
  templateUrl: './choix-poulailler.component.html',
  styleUrls: ['./choix-poulailler.component.scss']
})
export class ChoixPoulaillerComponent implements OnInit {
  poulaillerActuel:number;
  nomPoulailler:string;
  constructor(private poulaillerService : PoulaillerHttpService, private poulaillerSession : PoulaillerSessionService) { }

  getAllPoulailler() : Array<Poulailler> {
    return this.poulaillerService.getAll();
  }

  creerPoulailler() : void {
    this.poulaillerService.save(new Poulailler(this.nomPoulailler));
    this.nomPoulailler="";
  }

  supprimerPoulailler() : void {
    this.poulaillerService.delete(this.poulaillerActuel);
    this.poulaillerSession.poulailler=null;
  }

  changerPoulailler() : void {
    this.poulaillerService.setPoulaillerActuel(this.poulaillerActuel);
    this.poulaillerSession.loadPoulailler();
  }

  ngOnInit(): void {
  }

}
