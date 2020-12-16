package com.vermeg.bookstore.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.repositories.BookRepository;
import com.vermeg.bookstore.service.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/api/books"})
public class RestBookController {
    private final BookRepository bookRepository;
    @Autowired
    private OrderService orderService;

    @Autowired
    public RestBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
    @GetMapping("/{bookId}")
    public Optional<Book> getBook(@PathVariable long bookId) {
        return  bookRepository.findById(bookId);
    }

    @PostMapping("/add")
    Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);

    }

    @PutMapping("/update/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long
                                   bookId, @Valid @RequestBody Book bookRequest) throws IllegalAccessException {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalAccessException("BookId  not found");
        }
        return bookRepository.findById(bookId).map(book -> {
            book.setTitle(bookRequest.getTitle());
            book.setPrice(bookRequest.getPrice());
            book.setAuthor(bookRequest.getAuthor());
            book.setCover(bookRequest.getCover());
            return bookRepository.save(book);
        }).orElseThrow(() -> new IllegalAccessException("bookId not found"));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "bookId")
                                                Long bookId) throws IllegalAccessException {
        return bookRepository.findById(bookId).map(book -> {
            bookRepository.delete(book);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalAccessException("Article not found with id "));
    }

    @PostMapping("/somme")
    public double somme(@Valid @RequestBody List<Book> lb) throws NullPointerException {

        double total = orderService.computeTotalPrice(lb);
        System.out.println(total);
        return total;
    }


}