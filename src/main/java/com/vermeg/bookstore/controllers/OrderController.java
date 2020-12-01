package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.entities.Order;
import com.vermeg.bookstore.repositories.BookRepository;
import com.vermeg.bookstore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/list")
	public String listOrders(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "orders/listOrders";
	}

	@GetMapping("/add")
	public String showAddOrderForm(Model model) {
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		model.addAttribute("newOrder", order);
		model.addAttribute("bookList", bookRepository.findAll());
		return "orders/orderBook";
	}

	@PostMapping("/add")
	public String addOrder(@Valid Order order, BindingResult result) {
//        if(result.hasErrors()){
//            return result.toString();
//        }else {
		order.setOrderDate(LocalDate.now());
		orderRepository.save(order);
		return "redirect:list";
//        }

	}

	@GetMapping("/delete/{id}")
	public String deleteOrder(@PathVariable("id") long id, Model model) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
		orderRepository.delete(order);
		model.addAttribute("orders", orderRepository.findAll());
		return "redirect:../list";
	}

	@GetMapping("/edit/{id}")
	public String showOrderFormToUpdate(@PathVariable("id") long id, Model model) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));

		model.addAttribute("order", order);
		return "orders/updateOrder";
	}

	@PostMapping("edit/{id}")
	public String updateBook(@Valid Order order, @PathVariable("id") long id, BindingResult result, Model model) {
		order.setId(id);
		orderRepository.save(order);
		model.addAttribute("book", bookRepository.findAll());
		return "redirect:../list";
	}

	@GetMapping("/show/{id}")
	public String showOrderDetails(@PathVariable("id") long id, Model model) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
		// List<Book> articles = orderRepository.findBookByOrder(id);
		model.addAttribute("order", order);

		return "orders/showOrder";
	}
}
