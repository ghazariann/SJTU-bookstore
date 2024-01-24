package com.bookstore.bookstore_backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "bookCoverImages")
public class BookCoverImage {

    @Id
    private Long id;

    // private String bookId;
    private String image;
}
