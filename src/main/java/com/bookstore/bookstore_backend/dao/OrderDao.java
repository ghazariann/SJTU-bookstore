package com.bookstore.bookstore_backend.dao;

import com.bookstore.bookstore_backend.entity.Order;
import java.util.List;

public interface OrderDao {
   Order save(Order book);
   Order findById(long id);
    List<Order> findAll();
    void deleteById(long id);
}