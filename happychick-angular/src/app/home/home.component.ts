import { Component, OnInit } from '@angular/core';
import { Poulailler, Saison } from '../model';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  poulailler:Poulailler;

  constructor(private poulaillerService : PoulaillerHttpService ) { 
    this.loadPoulailler();
  }

  loadPoulailler(saison?:Saison) : void {
    if(saison) {
      this.poulaillerService.saisonSuivante(saison).subscribe(resp => {
        this.poulaillerService.getPoulaillerActuel().subscribe(
          reponse => {
            this.poulailler = reponse;
          }
        );
      });
    }
    else {
      this.poulaillerService.getPoulaillerActuel().subscribe(
        reponse => {
          this.poulailler = reponse;
        }
      );
    }
    
  }

  ngOnInit(): void {
  }

}
