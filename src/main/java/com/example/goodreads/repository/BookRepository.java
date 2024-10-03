package com.example.goodreads.repository;
import java.util.*;

import com.example.goodreads.model.*;


public interface BookRepository {
    ArrayList<Book> getBooks();
    Book getBookById(int bookId);
    Book addBook(Book book);
    Book updateBook(int bookId, Book book);
    void deleteBook(int bookId);
    Publisher getBookPublisher(int bookId);
    List<Author> getBookAuthors(int bookId);
}