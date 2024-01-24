package com.bookstore.bookstore_backend.dao;

import com.bookstore.bookstore_backend.entity.Book;
import java.util.List;

public interface BookDao {
    Book save(Book book);
    Book findById(long id);
    List<Book> findAll();
    void deleteById(long id);
    List<Book> findByTag(String tag);
    List<Book> searchBooksByName(String name);
}