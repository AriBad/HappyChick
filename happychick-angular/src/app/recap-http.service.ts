import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';
import { Recap } from './model';
import { PoulaillerSessionService } from './poulailler-session.service';

@Injectable({
  providedIn: 'root'
})
export class RecapHttpService {

  apiPath: string;
  recaps: Array<Recap> = new Array<Recap>();

  constructor(private http: HttpClient, private appConfig: AppConfigService, private poulaillerSessionService: PoulaillerSessionService) {
    this.apiPath = this.appConfig.apiBackEndUrl + "recap/";
    this.load();
  }

  load() {
    this.http.get<Array<Recap>>(this.apiPath).subscribe(response => {
      this.recaps = response;
    });
  }

  getAll(): Array<Recap> {
    return this.recaps;
  }

  findById(id: number): Observable<Recap> {
    return this.http.get<Recap>(this.apiPath+id);
  }

  getAllByPoulailler(id: number): Array<Recap> {
    return this.recaps.filter(recap => recap.poulailler.id == id);
  }

  // getRecapFromNow() Recap{
  //   return this.recaps.filter(recap => recap.poulailler.id == id);
  // }


}
