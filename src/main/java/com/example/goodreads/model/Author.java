package com.example.goodreads.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import com.example.goodreads.model.Book;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "authorid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column(name = "authorname")
    private String authorName;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    public Author(){

    }
    public Author(int authorId, String authorName, List<Book> books){
        this.authorId = authorId;
        this.authorName = authorName;
        this.books = books;
    }

    public int getAuthorId(){
        return authorId;
    }
    public void setAuthorIdId(int authorId){
        this.authorId = authorId;
    }

    public String getAuthorName(){
        return authorName;
    }
    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

}