import { Component, KeyValueDiffers, OnInit } from '@angular/core';
import { Couveuse, CouveuseComplete, Poulailler, Poule, Saison } from '../model';
import { PouleHttpService } from '../poule-http.service';
import { PoulaillerHttpService } from './poulailler-http.service';

@Component({
  selector: 'poulailler',
  templateUrl: './poulailler.component.html',
  styleUrls: ['./poulailler.component.scss']
})
export class PoulaillerComponent implements OnInit {
  nom:string;
  saison:Saison;
  couveuseoeufs:number;
  couveuseId:number;
  affichePoulesCouveuses:boolean = false;
  messageAfficherCouveuses:string = "Afficher Les Couveuses"
  poulailler : Poulailler;
  poulesCouveuses : Array<Couveuse> = new Array<Couveuse>;

  constructor(private poulaillerService: PoulaillerHttpService, private pouleService: PouleHttpService) {
    this.saison = new Saison();
    this.saison.listeCouveuses = new Array<Couveuse>;
    this.poulaillerService.getPoulaillerActuel().subscribe(
      reponse => {
        this.poulailler = reponse;
      }
    )
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

  saisonSuivante():void {
    this.poulesCouveuses.forEach((value,index) =>{
      this.saison.listeCouveuses.push(value);
    });
    this.poulaillerService.saisonSuivante(this.saison);
    this.saison=new Saison();
    this.saison.listeCouveuses = new Array<Couveuse>;
  }

  ajouterCouveuse():void {
    this.pouleService.findById(this.couveuseId).subscribe(resp=> {
      this.poulesCouveuses.push(new Couveuse(resp, this.couveuseoeufs));
      this.couveuseoeufs=null;
    });
    
  }

  supprimerCouveuse(id : number):void {
    this.poulesCouveuses.forEach((value,index) =>{
      if(value.poule.id == id) {
        this.poulesCouveuses.slice(index, 1);
      }
    });
  }

  getPoulesLibres(): Array<Poule>{
    return this.poulailler.listePoules.filter(poule=> poule.causeMort==null && poule.etat=="Liberte" && poule.femelle==true);
  }

  reinitialiser(){
    this.saison =null;
  }

  affichertableau(){
    if (this.affichePoulesCouveuses==true){
      this.affichePoulesCouveuses=false;
      this.messageAfficherCouveuses = "Afficher Les Couveuses";
    } else {
      this.affichePoulesCouveuses=true;
      this.messageAfficherCouveuses = "Cacher Les Couveuses";
    }
  }

  findPouleById(id: number) : Poule{
    let poule: Poule = new Poule();
    this.pouleService.findById(id).subscribe(resp=> {
      poule = resp;
    });
    return poule;
  }
  
}
