import {Injectable} from '@angular/core';
import {Book} from './book';
import {BOOKS} from './mock-books';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class SearchService {

  results: string[];
  private baseUrl = 'http://localhost:8080/BibliothekWS2017Server_Web_exploded';

  constructor(private http: HttpClient) {
  }

  // getBooks(): Observable<Book[]> {
  //   return of(BOOKS);
  // }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.baseUrl + '/searchDvds');
  }

  getBooksByInput(formBook: Book): Observable<Book[]> {
    return this.http.get<Book[]>(this.baseUrl + "/searchBooks", formBook);
  }


}
