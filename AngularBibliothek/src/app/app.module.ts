import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import {SearchService} from "./search.service";
import { BookDetailComponent } from './book-detail/book-detail.component';
import {HttpClientModule} from "@angular/common/http";

import {SelectModule} from 'ng-select';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    BookDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    SelectModule
  ],
  providers: [SearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
