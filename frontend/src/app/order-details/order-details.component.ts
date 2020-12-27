import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../models/book';
import { Order } from '../models/order';
import { OrderService } from '../services/order-service.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  public id;
  public books : Book[];
  order : Order ; 
  
  path: string = 'http://localhost:8080/uploads/';
  constructor(private orderService: OrderService, private router: Router ,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      params => {
        this.id = params.get('id');
      }
    );
    this.getOrderById(this.id);
    
  }
  getOrderById(id)
  {
    this.orderService.getOrder(this.id).subscribe(data => {
      this.order = data;
      this.books = this.order.books ;
      console.log(this.books);

    });
  }
  orderDetails(myObj) {
    this.router.navigate(['orderDetails' + '/' + myObj['id']]);
  }

}
