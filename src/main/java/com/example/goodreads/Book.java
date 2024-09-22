package com.example.goodreads;

public class Book {
    private int id;
    private String name;
    private String imageUrl;

    public Book(int id, String name, String imageUrl){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
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
}