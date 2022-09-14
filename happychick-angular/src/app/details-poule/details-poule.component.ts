import { Component, Input, OnInit } from '@angular/core';
import { Poulailler, Poule } from '../model';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';
import { PouleHttpService } from '../poule-http.service';

@Component({
  selector: 'app-details-poule',
  templateUrl: './details-poule.component.html',
  styleUrls: ['./details-poule.component.scss']
})
export class DetailsPouleComponent implements OnInit {

  
  @Input() poulailler: Poulailler;
  poules: Array<Poule>;

  constructor(private pouleService: PouleHttpService, private poulaillerService: PoulaillerHttpService) {
  
   }

  enVoyage(id: number){
    this.pouleService.delete(id);
  }

  ngOnInit(): void {
  }

}
