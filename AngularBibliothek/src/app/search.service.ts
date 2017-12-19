import {Injectable} from '@angular/core';
import {Book} from './book';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
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
    //
    // sha256.hash('stringToDigest')
    //
    // const hasher = new sha256.Hash();
    // // hasher.update(data1);
    // // hasher.update(data2);
    // const result = hasher.digest();

    return this.http.post<Book[]>(this.baseUrl + '/searchBooks', formBook
      /*, {
      headers: new HttpHeaders().set('Authorization', 'daniel:' + hash)
    }*/
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
