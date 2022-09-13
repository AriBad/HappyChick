import { Component, KeyValueDiffers, OnInit } from '@angular/core';
import { Couveuse, Poulailler, Poule, Saison } from '../model';
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
  couveuseId:number;
  couveuseNbOeufs:number;
  affichePoulesCouveuses:boolean = false;
  messageAfficherCouveuses:string = "Afficher Les Couveuses"
  poulailler : Poulailler;

  constructor(private poulaillerService: PoulaillerHttpService, private pouleService: PouleHttpService) {
    this.saison = new Saison();
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
    this.poulaillerService.saisonSuivante(this.saison);
    this.saison=new Saison();
  }

  ajouterCouveuse():void {
    this.saison.couveuses.push(new Couveuse(this.couveuseId, this.couveuseNbOeufs));
    this.couveuseId=null;
    this.couveuseNbOeufs=null;
  }

  supprimerCouveuse(id : number):void {
    this.saison.couveuses.forEach((value,index) =>{
      if(value.idPoule == id) {
        this.saison.couveuses.slice(index, 1);
      }
    });
  }

  getPoulesLibres(): Array<Poule>{
    return this.poulailler.listePoules.filter(poule=> poule.causeMort==null && poule.etat=="Liberte");
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
