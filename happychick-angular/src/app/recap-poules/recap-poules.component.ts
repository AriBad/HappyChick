import { Component, Input, OnInit } from '@angular/core';
import { Couveuse, Poulailler, Poule } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';
import { PouleHttpService } from '../poule-http.service';

@Component({
  selector: 'recap-poules',
  templateUrl: './recap-poules.component.html',
  styleUrls: ['./recap-poules.component.scss']
})
export class RecapPoulesComponent implements OnInit {

  poule : Poule;
  couveuse: Couveuse;
  poulailler:Poulailler = this.poulaillerSessionService.poulailler;
  poussins: Array<Poule>;
  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService,private poulaillerSessionService : PoulaillerSessionService) { }

   getNbPoussins(): number {
    this.poussins = this.poulailler.listePoules.filter(poule => poule.poussin == true);
    return this.poussins.length;
   }

   getNbPoulesInsouciantes(): number {
  
    return this.getPouleByTemperament("insouciante").length;
   }
   
   getNbPoulesSerieuses(): number {
    return this.getPouleByTemperament("serieuse").length;
   }
   
   getNbPoulesMamansPoule(): number {
    return this.getPouleByTemperament("mamanPoule").length;
   }
   
   getNbPoulesPsychopathes(): number {
    return this.getPouleByTemperament("psychopathe").length;
   }
   
   getNbPoulesPyromanes(): number {
    return this.getPouleByTemperament("pyromane").length;
   }

getNbPoulesCouveusesByTemperament(temperament: string){
 return this.getPoulesCouveuseByTemperament(temperament).length;
}

getNbPoulesMaternageByTemperament(temperament: string){
  return this.getPoulesMaternageByTemperament(temperament).length;
 }
 getBonheurMoyen(temperament: string){
  return this.getBonheur(temperament);
 }

getPouleByTemperament(temperament: string): Array<Poule> {
  return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.causeMort ==null);
}

getPoulesCouveuseByTemperament(temperament: string): Array<Poule> {
  return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.oeufsCouves!==0 && poule.causeMort ==null);
}

getPoulesMaternageByTemperament(temperament: string): Array<Poule> {
  return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.etat=="Maternage" && poule.causeMort ==null);
}
getBonheur(temperament: string): number{
  let bonheur : number = 0 ; 
  let  cpt : number =0 ;
  
  this.getPouleByTemperament(temperament).forEach(p => {
   bonheur = bonheur+p.bonheur;
   cpt++;
 });
 if (this.getPouleByTemperament(temperament).length == 0) {
  return 0;
 }
return bonheur/cpt; 

}


  ngOnInit(): void {
  }

}
