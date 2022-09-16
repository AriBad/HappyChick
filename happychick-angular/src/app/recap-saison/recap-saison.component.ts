import { Component, OnInit } from '@angular/core';
import { Poulailler, Recap } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { RecapHttpService } from '../recap-http.service';
import { SaisonService } from '../saison.service';

@Component({
  selector: 'recap-saison',
  templateUrl: './recap-saison.component.html',
  styleUrls: ['./recap-saison.component.scss']
})
export class RecapSaisonComponent implements OnInit {

  constructor(private recapService: RecapHttpService, private poulaillerSessionService: PoulaillerSessionService, private saisonService: SaisonService) { }


  getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  recapSaison(){

  }
  getNbPoulesVivantes() : number {
    return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort ==null ).length;
  }
  getAllRecapByThisPoulailler(){
    return this.recapService.getAllByPoulailler(this.poulaillerSessionService.poulailler.id);
  }

  getNowByThisPoulailler(): Array<Recap>{
    return this.recapService.getAllByPoulailler(this.poulaillerSessionService.poulailler.id).filter(recap => recap.saison==this.poulaillerSessionService.poulailler.saison && recap.annee == this.poulaillerSessionService.poulailler.annee);
  }
  ngOnInit(): void {
  }

}
