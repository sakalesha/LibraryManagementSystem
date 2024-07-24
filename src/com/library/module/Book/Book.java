package com.library.module.Book;

public class Book {
    private String bookID;
    private String title;
    private String author;
    private String publisher;
    private String publisherYear;

    public Book(String bookID, String title, String author, String publisher, String publisherYear) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publisherYear = publisherYear;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherYear() {
        return publisherYear;
    }

    public void setPublisherYear(String publisherYear) {
        this.publisherYear = publisherYear;
    }
}
