package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.entity.OrderItem;

import java.util.List;

public interface OrderItemService{
    OrderItem addOrderItem(OrderItem order); // POST

    OrderItem getOrderItemById(long id); // GET

    List<OrderItem> listOrderItems();  // GET

    OrderItem updateOrderItem(OrderItem order); // PUT

    void deleteOrderItem(long id); // DELETE
}
