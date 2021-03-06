import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ListBookComponent } from './list-book/list-book.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { AddBookComponent } from './add-book/add-book.component';
import { ListOrdersComponent } from './list-orders/list-orders.component';
import { OrderDetailsComponent } from './order-details/order-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ListBookComponent,
    NavbarComponent,
    UpdateBookComponent,
    AddBookComponent,
    ListOrdersComponent,
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
