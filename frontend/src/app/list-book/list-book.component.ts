import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../models/book';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrls: ['./list-book.component.css']
})
export class ListBookComponent implements OnInit {

  path: string = 'http://localhost:8080/uploads/';

  books: Book[];

  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    this.getBooks();

  }

  private getBooks() {
    this.bookService.getBooksList().subscribe(data => {
      this.books = data;

    });
  }
  updateBook(myObj) {
    this.router.navigate(['updateBook' + '/' + myObj['id']]);
  }

  deleteBook(book) {
    this.bookService.deleteBook(book).subscribe(response => this.getBooks())
  }
}
