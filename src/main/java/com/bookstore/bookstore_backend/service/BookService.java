package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book order); // POST

    Book getBookById(long id); // GET

    List<Book> listBooks();  // GET

    Book updateBook(Book order); // PUT

    void deleteBook(long id); // DELETE

    List<Book> findBooksByTag(String tagName);
    List<Book> searchBooksByName(String name);
}
