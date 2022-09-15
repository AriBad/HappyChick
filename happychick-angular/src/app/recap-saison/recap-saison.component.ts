import { Component, OnInit } from '@angular/core';
import { Poulailler } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';
import { PouleHttpService } from '../poule-http.service';
import { SaisonService } from '../saison.service';

@Component({
  selector: 'recap-saison',
  templateUrl: './recap-saison.component.html',
  styleUrls: ['./recap-saison.component.scss']
})
export class RecapSaisonComponent implements OnInit {

  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService,private poulaillerSessionService : PoulaillerSessionService,private saisonService : SaisonService) { }


  getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  recapSaison(){

  }
  getNbPoulesVivantes() : number {
    return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort ==null ).length;
  }
  recapSaisonDetaille(){
    
  }

  ngOnInit(): void {
  }

}
