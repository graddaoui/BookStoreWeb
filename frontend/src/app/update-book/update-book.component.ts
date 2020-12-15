import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../models/book';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {
  path:string = 'http://localhost:8080/uploads/';
  public id;
  public bookToUpdate;
  public title;
  public price;
  public author;
  public cover ;
  public date ;
  constructor(private service : BookService , private router : Router , private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      params => {
      this.id = params.get('id');
      }
      );
      this.bookToUpdate = this.service.getBook(this.id).subscribe(
      response => {
      //console.log(response);
      this.title = response["title"];
      this.price = response["price"];
      this.author = response["author"];
      this.cover = response["cover"];
      this.date = response["releaseDate"];
      console.log(response);
      }
      );
      
  }

  updateBook() {
  
    this.bookToUpdate = {
     'id': this.id,
     'title': this.title,
    'price': this.price,
    'author': this.author,
    'cover': this.cover,
    'releaseDate' : this.date
    }
    this.service.updateBook(this.bookToUpdate).subscribe(
      response => {
      console.log(response);
      }
      );
      this.router.navigate(['listBooks']);
   }
}
