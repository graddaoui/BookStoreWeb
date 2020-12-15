import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) { }

  getBooksList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getBook(id) {
    return this.http.get(this.baseUrl + '/' + id);
  }

  updateBook(myObj) {
    return this.http.put(this.baseUrl + '/update/' + myObj['id'], myObj);
  }

  deleteBook(myObj) {
    return this.http.delete(this.baseUrl + '/delete/' + myObj['id'], myObj)
  }
}
