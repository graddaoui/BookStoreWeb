import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './add-book/add-book.component';
import { ListBookComponent } from './list-book/list-book.component';
import { ListOrdersComponent } from './list-orders/list-orders.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { UpdateBookComponent } from './update-book/update-book.component';



const routes: Routes = [
{ path: "", pathMatch: "full", redirectTo: "app-navbar" },
{ path: "listBooks", component: ListBookComponent },
{ path: "updateBook/:id", component: UpdateBookComponent },
{path: "addBook", component: AddBookComponent},
{ path: "listOrders", component: ListOrdersComponent },
{ path: "orderDetails/:id", component: OrderDetailsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }