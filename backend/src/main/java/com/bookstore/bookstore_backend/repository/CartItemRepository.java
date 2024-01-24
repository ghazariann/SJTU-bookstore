package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
}
