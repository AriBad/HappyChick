import { Component, OnInit } from '@angular/core';
import { Poulailler } from '../model';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'recap-poulailler',
  templateUrl: './recap-poulailler.component.html',
  styleUrls: ['./recap-poulailler.component.scss']
})
export class RecapPoulaillerComponent implements OnInit {

  poulailler : Poulailler = new Poulailler("");
  constructor(poulaillerService: PoulaillerHttpService) {
    
   }

   getNbPoules() : number {
    return this.poulailler.listePoules.length;
   }
   

  ngOnInit(): void {
  }

}
