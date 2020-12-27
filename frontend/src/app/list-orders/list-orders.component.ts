import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../models/order';
import { OrderService } from '../services/order-service.service';


@Component({
  selector: 'app-list-orders',
  templateUrl: './list-orders.component.html',
  styleUrls: ['./list-orders.component.css']
})
export class ListOrdersComponent implements OnInit {
 orders : Order[];
  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {
    this.getOrders();

  }
  private getOrders() {
    this.orderService.getOrdersList().subscribe(data => {
      this.orders = data;

    });
  }
  orderDetail(myObj) {
    this.router.navigate(['orderDetails' + '/' + myObj['id']]);
  }

}
