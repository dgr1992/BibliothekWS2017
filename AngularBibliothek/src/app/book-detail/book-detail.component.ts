import {Component, Input, OnInit} from '@angular/core';
import { Book } from '../book';
import {Copy} from "../copy";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  @Input ('copiesData') copies: Copy[];

  constructor() { }

  ngOnInit() {
  }

}
