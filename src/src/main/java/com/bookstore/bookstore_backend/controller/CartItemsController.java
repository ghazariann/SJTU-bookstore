package com.bookstore.bookstore_backend.controller;

import com.bookstore.bookstore_backend.entity.CartItem;
import com.bookstore.bookstore_backend.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/cartItems")
@CrossOrigin(origins = "http://localhost:3000")

public class CartItemsController {

    private CartItemService cartService;

    @PostMapping
    public ResponseEntity<CartItem> addCart(@RequestBody CartItem cartItem) {
        CartItem newCartItem = cartService.addCart(cartItem);
        return new ResponseEntity<>(newCartItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartById(@PathVariable("id") long id) {
        CartItem cartItem = cartService.getCartById(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCarts() {
        List<CartItem> cartItems = cartService.listCarts();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCart(@PathVariable("id") long id, @RequestBody CartItem cartItem) {
        cartItem.setId(id);
        CartItem updatedCartItem = cartService.updateCart(cartItem);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
