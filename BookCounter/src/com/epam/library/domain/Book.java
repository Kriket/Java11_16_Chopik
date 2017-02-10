package com.epam.library.domain;


/**
 * Created by User on 10.02.2017.
 */
public class Book {

    int id;
    String brief;
    int dateOfPublishing;
    String author;

    public Book(int id, String brief, int dateOfPublishing, String author) {
        this.id = id;
        this.brief = brief;
        this.dateOfPublishing = dateOfPublishing;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getBrief() {
        return brief;
    }

    public int getDateOfPublishing() {
        return dateOfPublishing;
    }
}
