package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.OrderItem;
import org.springframework.stereotype.Service;
import com.bookstore.bookstore_backend.repository.OrderItemRepository;

import com.bookstore.bookstore_backend.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import com.bookstore.bookstore_backend.service.OrderItemService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(long id) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);
        return optionalOrderItem.orElse(null);
    }

    @Override
    public List<OrderItem> listOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        OrderItem existingOrderItem = orderItemRepository.findById(orderItem.getId()).orElseThrow(
                () -> new ResourceNotFoundException("OrderItem not found with ID: " + orderItem.getId()));

        existingOrderItem
                .setQuantity(orderItem.getQuantity() != 0 ? orderItem.getQuantity() : existingOrderItem.getQuantity());
        existingOrderItem.setPrice(orderItem.getPrice() != null ? orderItem.getPrice() : existingOrderItem.getPrice());

        return orderItemRepository.save(existingOrderItem);
    }

    @Override
    public void deleteOrderItem(long id) {
        orderItemRepository.deleteById(id);
    }
}
