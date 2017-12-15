import {Injectable} from '@angular/core';
import {Book} from './book';
import {BOOKS} from './mock-books';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Dvd} from './dvd';
import {Copy} from './copy';

@Injectable()
export class SearchService {

  results: string[];
  private baseUrl = 'http://localhost:8080';

  // private baseUrl = 'http://10.0.51.95:9000/BibliothekWS2017Server';

  constructor(private http: HttpClient) {
  }

  getBooksByInput(formBook: Book): Observable<Book[]> {
    return this.http.post<Book[]>(this.baseUrl + '/searchBooks', formBook);
  }

  getDvdsByInput(formDvd: Dvd): Observable<Dvd[]> {
    return this.http.post<Dvd[]>(this.baseUrl + '/searchDvds', formDvd);
  }

  getBookCopy(book: Book): Observable<Copy[]> {
    return this.http.post<Copy[]>(this.baseUrl + '/getCopiesByMedium', book);
  }


}