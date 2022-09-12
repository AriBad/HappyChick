import { Component, OnInit } from '@angular/core';
import { Poule } from '../model';
import { PouleHttpService } from '../poule-http.service';

@Component({
  selector: 'app-poule',
  templateUrl: './poule.component.html',
  styleUrls: ['./poule.component.scss']
})
export class PouleComponent implements OnInit {
  formPoule: Poule;

  constructor(private pouleService: PouleHttpService) { }

  ngOnInit(): void {
  }
  getAll(): Array<Poule> {
    return this.pouleService.getAll();
  }
  getAllwithPoulailler(): Array<Poule> {
    return this.pouleService.getAll();
  }

  create() {
    this.formPoule = new Poule();
  }

  update(id: number) {
    this.pouleService.findById(id).subscribe(resp => {
      this.formPoule = resp;
    });
  }

  save() {
    this.pouleService.save(this.formPoule);

    this.cancel();
  }

  cancel() {
    this.formPoule = null;
  }

  delete(id: number) {
    this.pouleService.delete(id);
  }
}
