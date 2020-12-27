package com.vermeg.bookstore.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.entities.Order;
import com.vermeg.bookstore.repositories.BookRepository;
import com.vermeg.bookstore.repositories.OrderRepository;
import com.vermeg.bookstore.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/api/orders"})
public class RestOrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BookRepository bookRepository;



	@GetMapping("")
	public List<Order> listOrders() {
		return (List<Order>) orderRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Order> getOrder(@PathVariable long id) {
		return orderRepository.findById(id);
	}




	
	

	

	

}
