package com.vermeg.bookstore;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.vermeg.bookstore.controllers.BookController;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		new File(BookController.uploadDirectory).mkdir();

		SpringApplication.run(BookstoreApplication.class, args);
	}

}
