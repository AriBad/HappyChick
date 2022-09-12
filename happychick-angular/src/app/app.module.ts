import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PouleComponent } from './poule/poule.component';
import { PoulaillerComponent } from './poulailler/poulailler.component';
import { HttpClientModule } from '@angular/common/http';
import { PouleMemoryService } from './poule-memory.service';

@NgModule({
  declarations: [
    AppComponent,
    PouleComponent,
    PoulaillerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [PouleMemoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
