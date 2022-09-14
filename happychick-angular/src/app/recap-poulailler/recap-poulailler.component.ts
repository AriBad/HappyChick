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

  poulailler:Poulailler = this.poulaillerSessionService.poulailler;
  constructor(private poulaillerService: PoulaillerHttpService,private poulaillerSessionService : PoulaillerSessionService) { }

  getNbPoules() : number {
    return this.poulailler.listePoules.length;
  }
   

  ngOnInit(): void {
  }

}
