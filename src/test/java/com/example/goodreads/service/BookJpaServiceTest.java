package com.example.goodreads.service;

import com.example.goodreads.model.Author;
import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.repository.PublisherJpaRepository;
import com.example.goodreads.service.BookJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.ExceptionHelper.doThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BookJpaServiceTest {

    @InjectMocks
    private BookJpaService bookService;

    @Mock
    private BookJpaRepository bookJpaRepository;

    @Mock
    private PublisherJpaRepository publisherJpaRepository;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBooks() {
        // Mocking publisher and authors
        Publisher publisher = new Publisher(1, "Publisher Name");
        Author author1 = new Author(1, "Author 1");
        Author author2 = new Author(2, "Author 2");

        // Creating a list of authors
        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        // Mocking the repository response with books containing authors and publisher
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1, "Book 1", "url1", publisher, authors));
        mockBooks.add(new Book(2, "Book 2", "url2", publisher, authors));

        // Defining behavior for mocked repository
        when(bookJpaRepository.findAll()).thenReturn(mockBooks);

        // Calling the service method
        ArrayList<Book> result = bookService.getBooks();

        // Verifying the result
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getName());
        assertEquals("Book 2", result.get(1).getName());
        assertEquals("Publisher Name", result.get(0).getPublisher().getPublisherName());
        assertEquals("Author 1", result.get(0).getAuthors().get(0).getAuthorName());
        assertEquals("Author 2", result.get(0).getAuthors().get(1).getAuthorName());
    }

    @Test
    public void testGetBookById_Success() {
        // Arrange
        Publisher publisher = new Publisher(1, "Publisher Name");
        Author author = new Author(1, "Author 1");
        Book mockBook = new Book(1, "Book 1", "url1", publisher, List.of(author));

        // Mocking the repository response
        when(bookJpaRepository.findById(1)).thenReturn(Optional.of(mockBook));

        // Act
        Book result = bookService.getBookById(1);

        // Assert
        assertEquals("Book 1", result.getName());
        assertEquals("Publisher Name", result.getPublisher().getPublisherName());
        assertEquals("Author 1", result.getAuthors().get(0).getAuthorName());
    }

    @Test
    public void testGetBookById_NotFound() {
        // Arrange
        when(bookJpaRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            bookService.getBookById(1);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        
    }

    @Test
    public void testGetBookPublisher_Success() {
        // Arrange
        Publisher publisher = new Publisher(1, "Publisher Name");
        Book mockBook = new Book(1, "Book 1", "url1", publisher, new ArrayList<>());

        when(bookJpaRepository.findById(1)).thenReturn(Optional.of(mockBook));

        // Act
        Publisher result = bookService.getBookPublisher(1);

        // Assert
        assertEquals("Publisher Name", result.getPublisherName());
    }

    @Test
    public void testGetBookPublisher_NotFound() {
        // Arrange
        when(bookJpaRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            bookService.getBookPublisher(1);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test for addBook
    @Test
    public void testAddBook_Success() {
        // Arrange
        Publisher publisher = new Publisher(1, "Publisher Name");
        Book newBook = new Book(1, "New Book", "url1", publisher, new ArrayList<>());


        when(publisherJpaRepository.findById(1)).thenReturn(Optional.of(publisher));
        when(bookJpaRepository.save(newBook)).thenReturn(newBook);

        // Act
        Book result = bookService.addBook(newBook);

        // Assert
        assertEquals("New Book", result.getName());
    }

    @Test
    public void testAddBook_PublisherNotFound() {
        // Arrange
        Publisher publisher = new Publisher(1, "Publisher Name");
        Book newBook = new Book(1, "New Book", "url1", publisher, new ArrayList<>());

        when(publisherJpaRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            bookService.addBook(newBook);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test for updateBook
    @Test
    public void testUpdateBook_Success() {
        // Arrange
        Publisher publisher = new Publisher(1, "Publisher Name");
        Book existingBook = new Book(1, "Old Book", "url1", publisher, new ArrayList<>());

        when(bookJpaRepository.findById(1)).thenReturn(Optional.of(existingBook));
        when(publisherJpaRepository.findById(1)).thenReturn(Optional.of(publisher));

        Book updatedBook = new Book(1, "Updated Book", "url2", publisher, new ArrayList<>());

        // Act
        Book result = bookService.updateBook(1, updatedBook);

        // Assert
        assertEquals("Updated Book", result.getName());
        assertEquals("url2", result.getImageUrl());
    }

    @Test
    public void testUpdateBook_NotFound() {
        // Arrange
        Book updatedBook = new Book(1, "Updated Book", "url2", null, new ArrayList<>());

        when(bookJpaRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            bookService.updateBook(1, updatedBook);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test for deleteBook
//    @Test
//    public void testDeleteBook_Success() {
//        // Arrange
//        doNothing().when(bookJpaRepository).deleteById(1);
//
//        // Act
//        bookService.deleteBook(1);
//
//        // Assert - no exception thrown, can be verified by observing behavior or using mock verifications
//    }

//    @Test
//    public void testDeleteBook_NotFound() {
//        // Arrange
//        when(bookJpaRepository.existsById(1)).thenReturn(false); // Simulate the book not existing
//
//        // Act & Assert
//        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//            bookService.deleteBook(1);
//        });
//
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//    }


    // Test for getBookAuthors
    @Test
    public void testGetBookAuthors_Success() {
        // Arrange
        Author author = new Author(1, "Author 1");
        Publisher publisher = new Publisher(1, "Publisher Name");
        Book mockBook = new Book(1, "Book 1", "url1", publisher, List.of(author));

        when(bookJpaRepository.findById(1)).thenReturn(Optional.of(mockBook));

        // Act
        List<Author> result = bookService.getBookAuthors(1);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Author 1", result.get(0).getAuthorName());
    }

    @Test
    public void testGetBookAuthors_NotFound() {
        // Arrange
        when(bookJpaRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            bookService.getBookAuthors(1);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
