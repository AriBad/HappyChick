import { Component, Input, OnInit } from '@angular/core';
import { Poulailler } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'recap-poulailler',
  templateUrl: './recap-poulailler.component.html',
  styleUrls: ['./recap-poulailler.component.scss']
})
export class RecapPoulaillerComponent implements OnInit {

  
  constructor(private poulaillerService: PoulaillerHttpService,private poulaillerSessionService : PoulaillerSessionService) { }

  getNbPoulesVivantes() : number {
    return this.getSessionPoulailler().listePoules.filter(poule => poule.causeMort ==null ).length;
  }
  
  getSessionPoulailler(): Poulailler {
    return this.poulaillerSessionService.poulailler;
  }

  ngOnInit(): void {
  }

}
