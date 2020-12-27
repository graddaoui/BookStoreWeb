import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/api/orders';

  constructor(private http: HttpClient) { }
  getOrdersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  
  getOrder(id) :Observable<any>
  {
    return this.http.get(this.baseUrl + '/' + id);
  }

}
