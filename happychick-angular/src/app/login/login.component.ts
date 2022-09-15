import { Component, OnInit } from '@angular/core';
import { UtilisateurHttpService } from './../utilisateur/utilisateur-http.service';
import { AuthService } from './../auth.service';
import { Router } from '@angular/router';
import { PoulaillerHttpService } from '../poulailler/poulailler-http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  identifiant: string;
  motDePasse: string;
  loginError: boolean = false;

  constructor(private authService: AuthService, private router: Router, private poulaillerService: PoulaillerHttpService) { }

  ngOnInit(): void {
  }

  valider() {
    this.authService.login(this.identifiant, this.motDePasse).subscribe((resp) => {
      this.authService.utilisateur = resp;
      this.router.navigate(["/home"]);
      this.poulaillerService.load();
    }, error => {
      if(error.status == "403") {
        this.loginError = true;
      }
    });;
  }

}
