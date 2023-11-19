package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.entity.BookCoverImage;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface BookCoverImageRepository extends MongoRepository<BookCoverImage, Long> { }
