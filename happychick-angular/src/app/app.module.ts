import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PouleComponent } from './poule/poule.component';
import { PoulaillerComponent } from './poulailler/poulailler.component';

@NgModule({
  declarations: [
    AppComponent,
    PouleComponent,
    PoulaillerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
