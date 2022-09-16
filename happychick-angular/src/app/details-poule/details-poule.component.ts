import { Component, Input, OnInit } from '@angular/core';
import { Couveuse, Poulailler, Poule } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';
import { PouleHttpService } from '../poule-http.service';
import { SaisonService } from '../saison.service';

@Component({
  selector: 'app-details-poule',
  templateUrl: './details-poule.component.html',
  styleUrls: ['./details-poule.component.scss']
})
export class DetailsPouleComponent implements OnInit {
  poules: Array<Poule>;
  vivantes: boolean = true;
  mortes: boolean = false;
  filtre: string = null;
  couveuseoeufs:number;

  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService, private poulaillerSessionService: PoulaillerSessionService, private saisonService: SaisonService) {

  }

  getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  coqsVivants(): boolean {
    if (this.getSessionPoulailler().listePoules.filter(poule => poule.femelle == false && poule.poussin==false && poule.causeMort ==null).length>0){
      return true;
    }
    return false;
  }

  ajouterCouveuse(id : number):void {
    this.pouleService.findById(id).subscribe(resp=> {
      this.saisonService.saison.listeCouveuses.push(new Couveuse(resp, this.couveuseoeufs));
      this.couveuseoeufs=null;
      this.saisonService.tempcouv.push(resp.id);
    });
    
  }

  CouveDeja(id:number): boolean {
    if (!this.saisonService.tempcouv.includes(id)) {
      return false;
    }
      return true;
  }

  filterPoule(mortes: boolean, vivantes: boolean, filtre: string): Array<Poule> {
    console.log(filtre);
    console.log(vivantes);
    console.log(mortes);
    if (filtre == "poussins") {
      console.log("ce sont des poussins");
      if (mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.poussin == true);
      } else if (mortes && !vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.causeMort != null && poule.poussin == true)
      } else if (!mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.causeMort == null && poule.poussin == true)
      } else {
        return null;
      }
    } else if (filtre == "coq") {
      console.log("ce sont des coqs");
      if (mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.femelle == false);
      } else if (mortes && !vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.causeMort != null && poule.femelle == false)
      } else if (!mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.causeMort == null && poule.femelle == false)
      } else {
        return null;
      }
    } else if (filtre == null || filtre=="") {
      console.log("ce sont toutes");
      if (mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules
      } else if (mortes && !vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.causeMort != null)
      } else if (!mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.causeMort == null)
      } else {
        return null;
      }
    } else {
      if (mortes && vivantes) {
        console.log("ce sont des poules");
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.temperament == filtre)
      } else if (mortes && !vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.temperament == filtre && poule.causeMort != null)
      } else if (!mortes && vivantes) {
        return this.poulaillerSessionService.poulailler.listePoules.filter(poule => poule.temperament == filtre && poule.causeMort == null)
      } else {
        return null;
      }
    }

  }

  enVoyage(id: number) {
    this.pouleService.delete(id);
  }

  ngOnInit(): void {
  }

}
