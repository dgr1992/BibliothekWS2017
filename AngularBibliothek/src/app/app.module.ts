import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { DetailviewComponent } from './detailview/detailview.component';
import { SearchComponent } from './search/search.component';
import {SearchService} from "./search.service";

@NgModule({
  declarations: [
    AppComponent,
    DetailviewComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [SearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
