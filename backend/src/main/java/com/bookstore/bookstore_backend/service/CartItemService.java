package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addCart(CartItem order);// POST

    CartItem getCartById(long id); // GET

    List<CartItem> listCarts(); // GET

    CartItem updateCart(CartItem order); // PUT

    void deleteCart(long id); // DELETE
}
