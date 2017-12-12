import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import {SearchService} from "../search.service";
import {IOption} from "ng-select";
import {Dvd} from "../dvd";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  formBook: Book = {title: '', author: '', isbn: '', releaseDate: '', publisher: '', id: ''};
  formDvd: Dvd = {title: '', director: '', asin: '', releaseDate: '', publisher: '', id: ''};

  books: Book[];

  selectedBook: Book;

  selectedMedium: String = '';

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

  searchBooks() {
   this.searchService.getBooksByInput(this.formBook);
  }

  searchDvds() {
    this.searchService.getDvdsByInput(this.formDvd);
  }

  searchOption: Array<IOption> = [
    {label: 'Books', value: 'books'},
    {label: 'Dvds', value: 'dvds'}
  ];
}
