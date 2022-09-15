import { Component, OnInit } from '@angular/core';
import { UtilisateurHttpService } from './../utilisateur/utilisateur-http.service';
import { AuthService } from './../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  identifiant: string;
  motDePasse: string;
  loginError: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  valider() {
    this.authService.login(this.identifiant, this.motDePasse).subscribe((resp) => {
      this.authService.utilisateur = resp;
      this.router.navigate(["/home"]);
    }, error => {
      if(error.status == "403") {
        this.loginError = true;
      }
    });;
  }

}
