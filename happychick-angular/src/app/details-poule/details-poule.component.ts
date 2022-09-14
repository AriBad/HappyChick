import { Component, Input, OnInit } from '@angular/core';
import { Poulailler, Poule } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';
import { PouleHttpService } from '../poule-http.service';

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

  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService, private poulaillerSessionService: PoulaillerSessionService) {

  }

  getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  filterPoule(mortes: boolean, vivantes: boolean, filtre: string): Array<Poule> {
    console.log(filtre);
    console.log(vivantes);
    console.log(mortes);
    if (filtre == "poussins") {
      console.log("ce sont des poussins");
      if (mortes && vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.poussin == true);
      } else if (mortes && !vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort != null && poule.poussin == true)
      } else if (!mortes && vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort == null && poule.poussin == true)
      } else {
        return null;
      }
    } else if (filtre == "coq") {
      console.log("ce sont des coqs");
      if (mortes && vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.femelle == false);
      } else if (mortes && !vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort != null && poule.femelle == false)
      } else if (!mortes && vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort == null && poule.femelle == false)
      } else {
        return null;
      }
    } else if (filtre == null || filtre=="") {
      console.log("ce sont toutes");
      if (mortes && vivantes) {
        return this.getSessionPoulailler().listePoules
      } else if (mortes && !vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort != null)
      } else if (!mortes && vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort == null)
      } else {
        return null;
      }
    } else {
      if (mortes && vivantes) {
        console.log("ce sont des poules");
        return this.getSessionPoulailler().listePoules.filter(poule => poule.temperament == filtre)
      } else if (mortes && !vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.temperament == filtre && poule.causeMort != null)
      } else if (!mortes && vivantes) {
        return this.getSessionPoulailler().listePoules.filter(poule => poule.temperament == filtre && poule.causeMort == null)
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
