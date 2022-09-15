import { Injectable } from '@angular/core';
import { Couveuse, Saison } from './model';

@Injectable({
  providedIn: 'root'
})
export class SaisonService {

  saison:Saison;
  tempcouv: Array<number> = new Array<number>;

  constructor() { 
    this.saison=new Saison();
    this.saison.listeCouveuses = new Array<Couveuse>;
  }
  
}
