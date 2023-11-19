package com.bookstore.bookstore_backend.dao;

import com.bookstore.bookstore_backend.entity.CartItem;
import java.util.List;

public interface CartItemDao {
    CartItem save(CartItem book);
    CartItem findById(long id);
    List<CartItem> findAll();
    void deleteById(long id);
}