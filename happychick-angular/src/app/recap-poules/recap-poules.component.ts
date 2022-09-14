import { Component, OnInit } from '@angular/core';
import { Couveuse, Poulailler, Poule } from '../model';
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
  poulailler: Poulailler;
  poussins: Array<Poule>;
  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService) {
    this.poulaillerService.getPoulaillerActuel().subscribe(
      reponse => {
        this.poulailler = reponse;
      }
    )
   }
   getNbPoussins(): number {
    this.poussins = this.poulailler.listePoules.filter(poule => poule.poussin == true);
    return this.poussins.length;
   }

   getNbPoulesInsouciantes(): number {
  
    return this.getPouleByTemperament("Insouciantes").length;
   }
   
   getNbPoulesSerieuses(): number {
    return this.getPouleByTemperament("Serieuses").length;
   }
   
   getNbPoulesMamansPoule(): number {
    return this.getPouleByTemperament("MamansPoule").length;
   }
   
   getNbPoulesPsychopathes(): number {
    return this.getPouleByTemperament("Psychopathes").length;
   }
   
   getNbPoulesPyromanes(): number {
    return this.getPouleByTemperament("Pyromanes").length;
   }

getNbPoulesCouveusesByTemperament(temperament: String){
 return this.getPoulesCouveuseByTemperament(temperament).length;
}

getNbPoulesMaternageByTemperament(temperament: String){
  return this.getPoulesMaternageByTemperament(temperament).length;
 }
 getBonheurMoyen(temperament: String){
  return this.getBonheur(temperament);
 }

getPouleByTemperament(temperament: String): Array<Poule> {
  return this.poulailler.listePoules.filter(poule => poule.temperament == temperament);
}

getPoulesCouveuseByTemperament(temperament: String): Array<Poule> {
  return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.oeufsCouves!==0);
}

getPoulesMaternageByTemperament(temperament: String): Array<Poule> {
  return this.poulailler.listePoules.filter(poule => poule.temperament == temperament && poule.maternage!==0);
}
getBonheur(temperament: String): number{
 let bonheur : number = 0 ; 
 let  cpt : number =0 ;
 this.getPouleByTemperament(temperament).forEach(p => bonheur = bonheur+p.bonheur && cpt++);

return bonheur/cpt; 

}


  ngOnInit(): void {
  }

}
