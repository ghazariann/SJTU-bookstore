package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.Book;
import com.bookstore.bookstore_backend.repository.TagRepository;
import com.bookstore.bookstore_backend.dao.BookDao;
import com.bookstore.bookstore_backend.service.BookService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.print.DocFlavor;
import javax.print.DocFlavor.STRING;

import com.bookstore.bookstore_backend.entity.Tag;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final TagRepository tagRepository;

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
            // existingBook.setImage(book.getImage()  != null? book.getImage() : existingBook.getImage());
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

   public List<Book> findBooksByTag(String tagInput) {
    // Split the tagInput string into an array of individual tags
    String[] tagArray = tagInput.split("\\s+");

    // Use a Set to avoid duplicate books
    Set<Book> uniqueBooks = new HashSet<>();

    for (String tag : tagArray) {
        List<String> relatedSubgenreNames = tagRepository.findRelatedSubgenres(tag);

        if (!relatedSubgenreNames.isEmpty()) {
            // Add the original tag to the list of related subgenres
            relatedSubgenreNames.add(tag);

            for (String subgenreName : relatedSubgenreNames) {
                // Fetch and accumulate books for each related subgenre
                uniqueBooks.addAll(bookDao.findByTag(subgenreName));
            }
        }
    }

    // Convert the Set back to a List
    return new ArrayList<>(uniqueBooks);
}

public List<Book> searchBooksByName(String name){
    return bookDao.searchBooksByName(name);
}
}
