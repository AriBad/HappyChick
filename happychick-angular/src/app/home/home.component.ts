import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Poulailler, Saison } from '../model';
import { PoulaillerSessionService } from '../poulailler-session.service';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

 poulailler:Poulailler = this.poulaillerSessionService.poulailler;
 
  constructor(private poulaillerService : PoulaillerHttpService, private poulaillerSessionService : PoulaillerSessionService,
    private authService: AuthService, private router: Router  ) { 

  }

  isConnected() :boolean {
    if(this.authService.utilisateur) {
      return true;
    } else {
      this.router.navigate(["/login"]);
      return false;
    }
  }

  ngOnInit(): void {
  }

}
