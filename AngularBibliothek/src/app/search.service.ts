import {Injectable} from '@angular/core';
import {Book} from './book';
import {BOOKS} from './mock-books';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class SearchService {

  results: string[];
  private baseUrl = 'http://localhost:8080';

  constructor() { }

  getBooks(): Observable<Book[]> {
    return of(BOOKS);
  }

  // getBooks (): Observable<String[]> {
  //   return this.http.get<String[]>(this.baseUrl + "searchBooks");
  // }


}
