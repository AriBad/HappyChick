import { Component, Input } from '@angular/core';
import { Poulailler, Saison } from './model';
import { PoulaillerHttpService } from './poulailler/poulailler-http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  init: boolean=true;
  poulaillerService: PoulaillerHttpService;
  poulailler: Poulailler;

  

  goHome(){
    this.init=false;
  }
}

