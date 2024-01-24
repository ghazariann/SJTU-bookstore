package com.bookstore.bookstore_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "author")
	private String author;

	@Column(name = "price")
	private float price;

	@Column(name = "description", length = 2000)
	private String description;

	@Column(name = "inventory")
	private float inventory;

	@Column(name = "tags")
    private String tags;
	// @Column(name = "image")
	// private String image;
	@Transient
    private BookCoverImage coverImage;

	@Override
	public String toString() {
		return "{id=" + String.valueOf(id) + "}";
	}
}
