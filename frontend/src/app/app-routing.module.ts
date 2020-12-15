import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListBookComponent } from './list-book/list-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';


const routes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "app-navbar" },
{ path: "listBooks", component: ListBookComponent },
{ path: "updateBook/:id", component: UpdateBookComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }