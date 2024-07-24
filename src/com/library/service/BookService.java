package com.library.service;

import java.util.ArrayList;
import java.util.List;

import com.library.module.Book.Book;

public class BookService {
    private static BookService instance;
    private List<Book> books;

    private BookService() {
        books = new ArrayList<>();
        // Sample data for demonstration
        books.add(new Book("1", "Book One", "Author One", "Publisher One", "2001"));
        books.add(new Book("2", "Book Two", "Author Two", "Publisher Two", "2002"));
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book searchBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getBookID().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
