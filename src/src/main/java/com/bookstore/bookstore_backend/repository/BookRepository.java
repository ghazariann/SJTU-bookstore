package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    
}
