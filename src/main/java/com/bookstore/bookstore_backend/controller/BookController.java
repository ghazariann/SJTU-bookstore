package com.bookstore.bookstore_backend.controller;

import com.bookstore.bookstore_backend.entity.Book;
import com.bookstore.bookstore_backend.service.BookService;
import lombok.AllArgsConstructor;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> listBooks() {
        List<Book> books = bookService.listBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/filterByTag")
    public ResponseEntity<List<Book>> filterByTag(@RequestBody Map<String, String> tagData) {
        String tag = tagData.get("tag");
        List<Book> books = bookService.findBooksByTag(tag);
        return ResponseEntity.ok(books);
    }
    @QueryMapping
    public List<Book> searchBooksByName(@Argument String name) {
        return bookService.searchBooksByName(name);
    }
}
