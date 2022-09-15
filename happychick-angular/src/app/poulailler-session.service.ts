import { Injectable } from '@angular/core';
import { Poulailler, Saison } from './model';
import { PoulaillerHttpService } from './poulailler/poulailler-http.service';

@Injectable({
  providedIn: 'root'
})
export class PoulaillerSessionService {
  poulailler: Poulailler;
  constructor(private poulaillerService: PoulaillerHttpService) {
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

  deconnexion() : void {
    this.poulailler=null;
  }
}
