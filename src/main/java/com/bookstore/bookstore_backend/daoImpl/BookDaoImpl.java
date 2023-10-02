package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.Book;
import com.bookstore.bookstore_backend.repository.BookRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.BookDao;

import java.util.List;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
