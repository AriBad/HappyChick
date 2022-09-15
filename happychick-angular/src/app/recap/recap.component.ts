import { Component, OnInit } from '@angular/core';
import { Poulailler, Recap, Saison } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { RecapHttpService } from '../recap-http.service';
import { SaisonService } from '../saison.service';

@Component({
  selector: 'recap-Historique',
  templateUrl: './recap.component.html',
  styleUrls: ['./recap.component.scss']
})
export class RecapComponent implements OnInit {

  constructor(private recapService: RecapHttpService, private poulaillerSessionService: PoulaillerSessionService, private saisonService: SaisonService) { }

  ngOnInit(): void {
  }

  getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  getAllRecapByThisPoulailler(){
    return this.recapService.getAllByPoulailler(this.poulaillerSessionService.poulailler.id);
  }

  getNowByThisPoulailler(): Array<Recap>{
    return this.recapService.getAllByPoulailler(this.poulaillerSessionService.poulailler.id).filter(recap => recap.saison==this.poulaillerSessionService.poulailler.saison && recap.annee == this.poulaillerSessionService.poulailler.annee);
  }

}
