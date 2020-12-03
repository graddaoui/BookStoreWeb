package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.entities.Order;
import com.vermeg.bookstore.repositories.BookRepository;
import com.vermeg.bookstore.repositories.OrderRepository;
import com.vermeg.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private JavaMailSender javaMailSender;

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
	public String addOrder(@Valid Order order, BindingResult result, Authentication auth) {
//        if(result.hasErrors()){
//            return result.toString();
//        }else {
		order.setOrderDate(LocalDate.now());
		orderRepository.save(order);
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String email = userDetails.getUsername();
		sendEmail(email, order);
		return "redirect:/accounts/dashboard";
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

	void sendEmail(String email, Order order) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Order");
		StringBuilder content = new StringBuilder(
				"Hello, You made an order that will be shipped to " + order.getAddress()
				+ ".\n Here is what you have ordered:\n"
		);
		for(Book book: order.getBooks()) {
			content.append(book.getTitle()).append("\t").append(book.getPrice()).append("\n");
		}
		List<Book> bookList = new ArrayList<>();
		for (Book book: order.getBooks())
			bookList.add(book);
		double total = orderService.computeTotalPrice(bookList) ;
		content.append("Total\t").append(total).append("\n");
		msg.setText(String.valueOf(content));
		javaMailSender.send(msg);
	}
}
