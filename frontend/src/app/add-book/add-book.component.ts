import { Component, OnInit } from '@angular/core';
import { BookService } from './../services/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  selectedFile: File;
  constructor(private service: BookService, private router: Router) { }
  ngOnInit() {
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0]
  }

  onUpload() {
    // this.http is the injected HttpClient
  const uploadData = new FormData();
  uploadData.append('myFile', this.selectedFile, this.selectedFile.name);
  console.log(uploadData);
  }

  createBook(myform) {
    this.service.createBook(myform).subscribe(
      response => {
        console.log(response);
      }
    );
    this.router.navigate(['listBooks']);
  }

}
