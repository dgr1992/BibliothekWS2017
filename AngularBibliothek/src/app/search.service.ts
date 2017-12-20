import {Injectable} from '@angular/core';
import {Book} from './book';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Dvd} from './dvd';
import {Copy} from './copy';

@Injectable()
export class SearchService {

  results: string[];
  private baseUrl = 'http://localhost:8080/BibliothekWS2017Server/REST';

  // private baseUrl = 'http://10.0.51.95:9000/BibliothekWS2017Server';

  constructor(private http: HttpClient) {
  }

  getBooksByInput(formBook: Book): Observable<Book[]> {
    return this.http.post<Book[]>(this.baseUrl + '/searchBooks', formBook
    );
  }

  getDvdsByInput(formDvd: Dvd): Observable<Dvd[]> {
    return this.http.post<Dvd[]>(this.baseUrl + '/searchDvds', formDvd);
  }

  getBookCopy(book: Book): Observable<Copy[]> {
    return this.http.post<Copy[]>(this.baseUrl + '/getCopiesByMedium', book);
  }

  getDvdCopy(dvd: Dvd): Observable<Copy[]> {
    return this.http.post<Copy[]>(this.baseUrl + '/getCopiesByMedium', dvd);
  }

}
