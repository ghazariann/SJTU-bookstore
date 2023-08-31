package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.Book;
import com.bookstore.bookstore_backend.dao.BookDao;
import com.bookstore.bookstore_backend.service.BookService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Override
    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> optionalBook = Optional.ofNullable(bookDao.findById(id));
        return optionalBook.orElse(null);
    }

    @Override
    public List<Book> listBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> existingBookOptional = Optional.ofNullable(bookDao.findById(book.getId()));
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            existingBook.setId(book.getId() != 0 ? book.getId() : existingBook.getId());
            existingBook.setName(book.getName() != null? book.getName() : existingBook.getName());
            existingBook.setAuthor(book.getAuthor() != null ? book.getAuthor() : existingBook.getAuthor());
            existingBook.setDescription(book.getDescription() != null ? book.getDescription() : existingBook.getDescription());
            existingBook.setImage(book.getImage()  != null? book.getImage() : existingBook.getImage());
            existingBook.setPrice(book.getPrice() != 0 ? book.getPrice() : existingBook.getPrice());
            existingBook.setType(book.getType() != null? book.getType() : existingBook.getType());
            existingBook.setInventory(book.getInventory() != 0? book.getInventory() : existingBook.getInventory());

            return bookDao.save(existingBook);
        }
        return null;
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteById(id);
    }
}
