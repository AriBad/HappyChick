import { Injectable } from '@angular/core';
import { Poule } from './model';

@Injectable({
  providedIn: 'root'
})
export class PouleMemoryService {
  poules: Array<Poule> = new Array<Poule>();
  constructor() { }

  findAll(): Array<Poule> {
    return this.poules;
  }

  findById(id: number): Poule {
    return this.poules.find(poule => poule.id == id);
  }

  save(poule: Poule) {
    if (poule.id) { // en modification
      let index = this.poules.findIndex(po => po.id == poule.id);
      this.poules[index] =poule;
    } else { // en création
      let max = 0;
      for (let po of this.poules) {
        if (po.id > max) {
          max = po.id;
        }
      }

      poule.id = ++max;

      this.poules.push(poule);
    }
  }

  delete(id: number) {
    let index = this.poules.findIndex(po => po.id == id);

    if (index != -1) {
      this.poules.splice(index, 1);
    }
  }
}

