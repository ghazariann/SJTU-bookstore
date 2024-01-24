package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.CartItem;
import com.bookstore.bookstore_backend.dao.CartItemDao;
import com.bookstore.bookstore_backend.service.CartItemService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CartItemServiceImpl implements CartItemService {

    private final CartItemDao cartItemDao;

    @Override
    public CartItem addCart(CartItem cartItem) {
        return cartItemDao.save(cartItem);
    }

    @Override
    public CartItem getCartById(long id) {
        Optional<CartItem> optionalCart = Optional.ofNullable(cartItemDao.findById(id));
        return optionalCart.get();
    }

    @Override
    public List<CartItem> listCarts() {
        return cartItemDao.findAll();
    }

    @Override
    public CartItem updateCart(CartItem cartItem) {
        CartItem existingCartItem = cartItemDao.findById(cartItem.getId()); // err

        existingCartItem.setId(cartItem.getId() != 0 ? cartItem.getId() : existingCartItem.getId());
        existingCartItem
                .setQuantity(cartItem.getQuantity() != 0 ? cartItem.getQuantity() : existingCartItem.getQuantity());

        return cartItemDao.save(existingCartItem);
    }

    @Override
    public void deleteCart(long id) {
        cartItemDao.deleteById(id);
    }

}
