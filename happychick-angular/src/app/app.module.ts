import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PouleComponent } from './poule/poule.component';
import { PoulaillerComponent } from './poulailler/poulailler.component';
import { InitComponent } from './init/init.component';
import { HomeComponent } from './home/home.component';
import { DetailsPouleComponent } from './details-poule/details-poule.component';
import { RecapPoulaillerComponent } from './recap-poulailler/recap-poulailler.component';
import { RecapPoulesComponent } from './recap-poules/recap-poules.component';
import { RecapUtilisateurComponent } from './recap-utilisateur/recap-utilisateur.component';
import { MenuComponent } from './menu/menu.component';
import { RecapSaisonComponent } from './recap-saison/recap-saison.component';
import { GuideComponent } from './guide/guide.component';
import { TropheeComponent } from './trophee/trophee.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ChoixPoulaillerComponent } from './choix-poulailler/choix-poulailler.component';
import { LoginComponent } from './login/login.component';
import { UtilisateurComponent } from './utilisateur/utilisateur.component';
import { APIInterceptor } from './api.interceptor';
import { RecapComponent } from './recap/recap.component';
import { InscriptionComponent } from './inscription/inscription.component';

@NgModule({
  declarations: [
    AppComponent,
    PouleComponent,
    PoulaillerComponent,
    InitComponent,
    HomeComponent,
    DetailsPouleComponent,
    RecapPoulaillerComponent,
    RecapPoulesComponent,
    RecapUtilisateurComponent,
    MenuComponent,
    RecapSaisonComponent,
    GuideComponent,
    TropheeComponent,
    ChoixPoulaillerComponent,
    LoginComponent,
    UtilisateurComponent,
    RecapComponent,
    InscriptionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: APIInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
