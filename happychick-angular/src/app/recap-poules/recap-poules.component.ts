import { Component, OnInit } from '@angular/core';
import { Couveuse, Poule } from '../model';
import { PouleHttpService } from '../poule-http.service';

@Component({
  selector: 'recap-poules',
  templateUrl: './recap-poules.component.html',
  styleUrls: ['./recap-poules.component.scss']
})
export class RecapPoulesComponent implements OnInit {

  poule : Poule;
  couveuse: Couveuse;
  constructor(private pouleService: PouleHttpService) {
    
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
