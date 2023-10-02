package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.CartItem;
import com.bookstore.bookstore_backend.repository.CartItemRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.CartItemDao;

import java.util.List;

@Repository
@AllArgsConstructor
public class CartItemDaoImpl implements CartItemDao {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        cartItemRepository.deleteById(id);
    }
}
