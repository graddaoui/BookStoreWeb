package com.vermeg.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vermeg.bookstore.entities.Book;
@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long>{

}
