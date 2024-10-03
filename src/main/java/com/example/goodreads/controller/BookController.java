package com.example.goodreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.example.goodreads.service.BookJpaService;
import com.example.goodreads.model.*;


@RestController
public class BookController {

    @Autowired
    private BookJpaService bookService;

    @GetMapping("/books/{bookId}/publisher")
    public Publisher getBookPublisher(@PathVariable("bookId") int bookId){
        return bookService.getBookPublisher(bookId);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book) {
        return bookService.updateBook(bookId, book);
    }

    @PostMapping("/publisher/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId) {
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId){
        bookService.deleteBook(bookId);
    }

    @GetMapping("/books/{bookId}/authors")
    public List<Author> getBookAuthors(@PathVariable("bookId") int bookId){
        return bookService.getBookAuthors(bookId);
    }

}
