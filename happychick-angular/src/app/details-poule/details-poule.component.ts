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

  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService, private poulaillerSessionService: PoulaillerSessionService) {
  
   }
   getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  enVoyage(id: number){
    this.pouleService.delete(id);
  }

  ngOnInit(): void {
  }

}
