import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',

})
export class BookService {

  private baseUrl = 'http://localhost:8080/api/books';
  private fileUrl = 'http://localhost:8080/api/files';
  book: any;

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

  createBook(myform) {
    this.book = {
      'title': myform.value.title,
      'author': myform.value.author,
      'price': myform.value.price,
      'releaseDate': myform.value.releaseDate,
      'cover': myform.value.cover
    }
    return this.http.post(this.baseUrl + '/add', this.book);

  }

  upload(file): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    return this.http.post(this.fileUrl + '/upload', formData);
  }
}
