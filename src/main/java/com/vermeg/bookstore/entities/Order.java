package com.vermeg.bookstore.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_date")
	private LocalDate orderDate;

	@NotBlank(message = "Address is mandatory")
	@Column(name = "address")
	private String address;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "orders_books", joinColumns = {
			@JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "books_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Book> books = new HashSet<>();

	/*
	 * @ManyToMany(fetch=FetchType.EAGER)
	 * 
	 * @JoinTable( name = "orders_books", joinColumns = @JoinColumn(name =
	 * "order_id"), inverseJoinColumns = @JoinColumn(name = "book_id")) private
	 * List<Book> books ;
	 */

	public Order() {
	}

	public Order(LocalDate orderDate, String address, Set<Book> books) {
		this.orderDate = orderDate;
		this.address = address;
		this.books = books;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
