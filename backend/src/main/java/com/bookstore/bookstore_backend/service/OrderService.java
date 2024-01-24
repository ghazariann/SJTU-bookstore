package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.entity.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);// POST

    Order getOrderById(long id); // GET

    List<Order> listOrders(); // GET

    Order updateOrder(Order order); // PUT

    void deleteOrder(long id); // DELETE
}
