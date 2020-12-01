package com.vermeg.bookstore.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Title is mandatory")
	@Column(name = "title")
	private String title;

	@NotNull
	@Column(name = "price")
	private double price;

	@NotBlank(message = "Author is mandatory")
	@Column(name = "author")
	private String author;

	@NotBlank(message = "Cover is mandatory")
	@Column(name = "cover")
	private String cover;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "release_date")
	private LocalDate releaseDate;
	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	Set<Order> orders = new HashSet<>();

	public Book() {
	}

	public Book(String title, double price, String author, String cover, LocalDate releaseDate) {
		this.title = title;
		this.price = price;
		this.author = author;
		this.cover = cover;
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
