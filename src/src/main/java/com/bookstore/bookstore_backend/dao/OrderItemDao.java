package com.bookstore.bookstore_backend.dao;

import com.bookstore.bookstore_backend.entity.OrderItem;
import java.util.List;

public interface OrderItemDao {
    OrderItem save(OrderItem book);
    OrderItem findById(long id);
    List<OrderItem> findAll();
    void deleteById(long id);
}