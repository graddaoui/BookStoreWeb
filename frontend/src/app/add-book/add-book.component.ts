import { Component, OnInit } from '@angular/core';
import { BookService } from './../services/book.service';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  public cover;

  constructor(private service: BookService, private router: Router) { }

  ngOnInit() {
  }


  createBook(myform) {

    const ctrl = new FormControl(this.cover.name);
    myform.form.addControl('cover', ctrl);

    this.service.upload(this.cover).subscribe(data => {

    });

    
    this.service.createBook(myform).subscribe(
      response => {
        this.router.navigate(['listBooks']);
      }
    );
  }

  changeCover(event) {
    this.cover = event.target.files[0];
  }

}
