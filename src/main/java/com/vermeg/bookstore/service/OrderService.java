package com.vermeg.bookstore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.vermeg.bookstore.entities.Book;

@Service()

public class OrderService {
	public double computeTotalPrice(List<Book> lb) {
		double totalPrice = 0;
		for (Book book : lb) {
			totalPrice += book.getPrice();
		}
		return totalPrice;
	}

}
