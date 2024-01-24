package com.bookstore.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore // Ignore this field when converting to JSON
    private Order order;

    @Column()
    private Integer quantity;

    @Column()
    private Double price;

    @Override
    public String toString() {
        return "{id=" + String.valueOf(id) + ", book=" + book.toString() + ", quantity=" + quantity + ", price=" + price
                + "}";
    }

}