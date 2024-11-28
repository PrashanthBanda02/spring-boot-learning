package com.example.goodreads.model;
import com.example.goodreads.model.Publisher;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import com.example.goodreads.model.Author;
import java.util.ArrayList;
import java.util.List;




@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "imageurl")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="publisherid")
    private Publisher publisher;


    @ManyToMany
    @JoinTable( name = "book_author",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "authorid")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();


    // Hibernate requires a default constructor with no arguments in our entity class
    // in order to create new instances of those classes and perform some operations internally.
    public Book() {}

    public Book(int id, String name, String imageUrl, List<Author> authors){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.authors = authors;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setId (int id){
        this.id = id;
    }

    public void setName (String name){
        this.name = name;
    }
    public void setImageUrl (String imageUrl){
        this.imageUrl = imageUrl;
    }

    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    
}