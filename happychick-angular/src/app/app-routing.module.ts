import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsPouleComponent } from './details-poule/details-poule.component';
import { GuideComponent } from './guide/guide.component';
import { HomeComponent } from './home/home.component';
import { InitComponent } from './init/init.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { LoginComponent } from './login/login.component';
import { RecapSaisonComponent } from './recap-saison/recap-saison.component';
import { TropheeComponent } from './trophee/trophee.component';

const routes: Routes = [
  {path:"", component: InitComponent, pathMatch: "full"},
  {path:"login", component:LoginComponent},
  {path:"home", component: HomeComponent},
  {path:"detailsPoule", component: DetailsPouleComponent},
  {path:"historique", component: RecapSaisonComponent},
  {path:"guide", component: GuideComponent},
  {path:"trophees", component: TropheeComponent},
  {path:"inscription", component:InscriptionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
