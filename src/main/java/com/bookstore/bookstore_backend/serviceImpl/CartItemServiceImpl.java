package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.CartItem;
import com.bookstore.bookstore_backend.repository.CartItemRepository;
import com.bookstore.bookstore_backend.service.CartItemService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CartItemServiceImpl implements CartItemService{
    
    private CartItemRepository cartRepository;

    @Override
    public CartItem addCart(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem getCartById(long id) {
        Optional<CartItem> optionalCart = cartRepository.findById(id);
        return optionalCart.get();
    }

    @Override
    public List<CartItem> listCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem updateCart(CartItem cartItem) {
        CartItem existingCartItem = cartRepository.findById(cartItem.getId()).get();
        existingCartItem.setId(cartItem.getId() != 0 ? cartItem.getId() : existingCartItem.getId());
        existingCartItem.setQuantity(cartItem.getQuantity() != 0 ? cartItem.getQuantity() : existingCartItem.getQuantity());

        return cartRepository.save(existingCartItem);
    }

    @Override
    public void deleteCart(long id) {
        cartRepository.deleteById(id);
    }

}
