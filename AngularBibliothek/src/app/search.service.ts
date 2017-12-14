import {Injectable} from '@angular/core';
import {Book} from './book';
import {BOOKS} from './mock-books';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Dvd} from './dvd';

@Injectable()
export class SearchService {

  results: string[];
  private baseUrl = 'http://localhost:8080';
  // private baseUrl = 'http://10.0.51.95:9000/BibliothekWS2017Server';

  constructor(private http: HttpClient) {
  }

  // getBooks(): Observable<Book[]> {
  //   return of(BOOKS);
  // }

  // getBooks(): Observable<Book[]> {
  //   return this.http.get<Book[]>(this.baseUrl + '/searchDvds');
  // }

  getBooksByInput(formBook: Book): Observable<Book[]> {
    const req = this.http.post<Book[]>(this.baseUrl + '/searchBooks', formBook);
    req.subscribe();
    return req;
  }

  getDvdsByInput(formDvd: Dvd): Observable<Dvd[]> {
    const req = this.http.post<Dvd[]>(this.baseUrl + '/searchDvds', formDvd);
    req.subscribe();
    return req;
  }


}
