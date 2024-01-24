package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.tags LIKE %:tag%")
    List<Book> findByTagPattern(@Param("tag") String tag);
    List<Book> findByNameContainingIgnoreCase(String name);
}
