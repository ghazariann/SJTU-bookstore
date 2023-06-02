package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.Book;
import com.bookstore.bookstore_backend.repository.BookRepository;
import com.bookstore.bookstore_backend.service.BookService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.get();
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId()).get();

        existingBook.setId(book.getId() != 0 ? book.getId() : existingBook.getId());
        existingBook.setName(book.getName() != null? book.getName() : existingBook.getName());
        existingBook.setAuthor(book.getAuthor() != null ? book.getAuthor() : existingBook.getAuthor());
        existingBook.setDescription(book.getDescription() != null ? book.getDescription() : existingBook.getDescription());
        existingBook.setImage(book.getImage()  != null? book.getImage() : existingBook.getImage());
        existingBook.setPrice(book.getPrice() != 0 ? book.getPrice() : existingBook.getPrice());
        existingBook.setType(book.getType() != null? book.getType() : existingBook.getType());

//        Book updatedBook = booksRepository.save(existingBook);

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
