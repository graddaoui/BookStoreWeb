package com.vermeg.bookstore.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;

@Controller
@RequestMapping("/book/")
public class BookController {
	private final BookRepository bookRepository;
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping("list")
	public String listBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "book/listbooks";
	}

	@GetMapping("add")
	public String showAddBookForm(Book book) {
		return "book/addBook";
	}

	@PostMapping("add")
	public String addBook(@Valid Book book, BindingResult result, @RequestParam("files") MultipartFile[] files) {

		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		book.setCover(fileName.toString());

		bookRepository.save(book);
		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
		bookRepository.delete(book);
		model.addAttribute("books", bookRepository.findAll());
		return "book/listbooks";
	}

	@GetMapping("edit/{id}")
	public String showBookFormToUpdate(@PathVariable("id") long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

		model.addAttribute("book", book);
		return "book/updateBook";
	}

	@PostMapping("edit/{id}")

	public String updateBook(@Valid Book book, BindingResult result, Model model,
			@RequestParam("files") MultipartFile[] files) {
		/*
		 * if (result.hasErrors()) { //book.setId(id); return "book/updateBook"; }
		 */
		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		book.setCover(fileName.toString());

		System.out.println(result.getErrorCount());
		bookRepository.save(book);
		model.addAttribute("book", bookRepository.findAll());
		return "redirect:../list";
	}

	@GetMapping("show/{id}")
	public String showBookDetails(@PathVariable("id") long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

		model.addAttribute("book", book);

		return "book/showBook";
	}

	@GetMapping("bookDetails/{id}")
	public String bookDetails(@PathVariable("id") long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

		model.addAttribute("book", book);
		return "book/bookDetails";
	}

}
