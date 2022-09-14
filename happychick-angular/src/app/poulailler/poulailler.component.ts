import { Component, EventEmitter, Input, KeyValueDiffers, OnInit, Output } from '@angular/core';
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
  @Input() poulailler : Poulailler;
  @Output() itemEvent = new EventEmitter<Saison>();
  tempcouv: Array<number> = new Array<number>;
  submitDisabled=false;


  constructor(private poulaillerService: PoulaillerHttpService, private pouleService: PouleHttpService) {
    this.saison = new Saison();
    this.saison.listeCouveuses = new Array<Couveuse>;
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
    this.itemEvent.emit(this.saison);
    this.saison=new Saison();
    this.saison.listeCouveuses = new Array<Couveuse>;
    this.reinitialiser();
  }

  oeufsDispos():number {
    let oeufsDispos = this.poulailler.oeufs;
    this.saison.listeCouveuses.forEach((value) =>{
      oeufsDispos-= value.oeufs;
    });

    if(this.saison.securite) {
      oeufsDispos-=20;
    }
    if(this.saison.taille) {
      oeufsDispos-=20;
    }
    if(this.saison.nourriture) {
      oeufsDispos -= this.saison.nourriture*50;
    }
    this.submitDisabled = oeufsDispos < 0;
    return oeufsDispos; 
  }

  ajouterCouveuse():void {
    this.pouleService.findById(this.couveuseId).subscribe(resp=> {
      this.saison.listeCouveuses.push(new Couveuse(resp, this.couveuseoeufs));
      this.couveuseoeufs=null;
      this.tempcouv.push(resp.id);
      this.couveuseId=null;
    });
    
  }

  supprimerCouveuse(id : number):void {
    this.saison.listeCouveuses.forEach((value,index) =>{
      if(value.poule.id == id) {
        this.saison.listeCouveuses.splice(index, 1);
      }
    });
    this.tempcouv.forEach((value,index) =>{
      if(value == id) {
        this.tempcouv.splice(index, 1);
      }
    });
  }



  getPoulesLibres(): Array<Poule>{
    return this.poulailler.listePoules.filter(poule=> poule.causeMort==null && poule.etat=="Liberte" && poule.poussin==false && poule.femelle==true && !this.tempcouv.includes(poule.id));
  }

  reinitialiser(){
    this.saison =new Saison();
    this.saison.listeCouveuses = new Array<Couveuse>;
    this.couveuseId=null;
    this.couveuseoeufs=null;
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
