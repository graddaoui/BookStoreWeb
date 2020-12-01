package com.vermeg.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vermeg.bookstore.entities.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
