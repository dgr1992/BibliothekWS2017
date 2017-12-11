import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class SearchService implements OnInit {

  results: string[];
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // Make the HTTP request:
    this.http.get(this.baseUrl + '/searchBooks').subscribe(data => {
      // Read the result field from the JSON response.
      this.results = data['results'];
    });
  }

}
