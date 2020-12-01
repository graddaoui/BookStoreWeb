package com.vermeg.bookstore.repositories;


import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.entities.Order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
   /*@Query("FROM books b WHERE b.id in (SELECT books_id FROM orders_books WHERE order_id = ?1)")
    List<Book> findBookByOrder(long id) ;*/
	
}
