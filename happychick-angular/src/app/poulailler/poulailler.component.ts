import { Component, KeyValueDiffers, OnInit } from '@angular/core';
import { Couveuse, Poulailler, Saison } from '../model';
import { PoulaillerHttpService } from './poulailler-http.service';

@Component({
  selector: 'app-poulailler',
  templateUrl: './poulailler.component.html',
  styleUrls: ['./poulailler.component.scss']
})
export class PoulaillerComponent implements OnInit {
  nom:string;
  saison:Saison;
  couveuseId:number;
  couveuseNbOeufs:number;
  couveuseIdASupprimer:number;

  constructor(private poulaillerService: PoulaillerHttpService) {
    this.saison = new Saison();
  }

  ngOnInit(): void {
  }

  save():void {
    this.poulaillerService.save(new Poulailler(this.nom));
  }

  delete(id: number):void {
    this.poulaillerService.delete(id);
  }

  getAll():Array<Poulailler> {
    return this.poulaillerService.getAll();
  }

  choisirPoulaillerActuel(id:number):void {
    this.poulaillerService.setPoulaillerActuel(id);
  }

  getPoulaillerActuel():Poulailler {
    return this.poulaillerService.getPoulaillerActuel();
  }

  saisonSuivante():void {
    this.poulaillerService.saisonSuivante(this.saison);
    this.saison=new Saison();
  }

  ajouterCouveuse():void {
    this.saison.couveuses.push(new Couveuse(this.couveuseId, this.couveuseNbOeufs));
    this.couveuseId=null;
    this.couveuseNbOeufs=null;
  }

  supprimerCouveuse():void {
    this.saison.couveuses.forEach((value,index) =>{
      if(value.idPoule == this.couveuseIdASupprimer) {
        this.saison.couveuses.slice(index, 1);
      }
    });
  }

  getPoulesLibres() {
    return this.poulaillerService.getPoulesLibres();
  }
  
}
