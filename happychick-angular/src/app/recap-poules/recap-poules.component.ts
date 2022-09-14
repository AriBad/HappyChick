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
  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService) {
    this.poulaillerService.getPoulaillerActuel().subscribe(
      reponse => {
        this.poulailler = reponse;
      }
    )
   }

   getNbPoulesInsouciantes(): number {
    return this.pouleService.getPouleByTemperament("Insouciantes").length;
   }
   
   getNbPoulesSerieuses(): number {
    return this.pouleService.getPouleByTemperament("Serieuses").length;
   }
   
   getNbPoulesMamansPoule(): number {
    return this.pouleService.getPouleByTemperament("MamansPoule").length;
   }
   
   getNbPoulesPsychopathes(): number {
    return this.pouleService.getPouleByTemperament("Psychopathes").length;
   }
   
   getNbPoulesPyromanes(): number {
    return this.pouleService.getPouleByTemperament("Pyromanes").length;
   }

getNbPoulesCouveusesByTemperament(temperament: String){
 return this.pouleService.getPoulesCouveuseByTemperament(temperament).length;
}

getNbPoulesMaternageByTemperament(temperament: String){
  return this.pouleService.getPoulesMaternageByTemperament(temperament).length;
 }
 getBonheurMoyen(temperament: String){
  return this.pouleService.getBonheurMoyen(temperament);
 }

  ngOnInit(): void {
  }

}
