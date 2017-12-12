import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import {SearchService} from "../search.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  books: Book[];

  selectedBook: Book;

  constructor(private searchService: SearchService) { }

  ngOnInit() {
    this.getBooks();
  }

  onSelect(book: Book): void {
    this.selectedBook = book;
  }

  getBooks(): void {
    this.searchService.getBooks()
      .subscribe(books => this.books = books);
  }

}
