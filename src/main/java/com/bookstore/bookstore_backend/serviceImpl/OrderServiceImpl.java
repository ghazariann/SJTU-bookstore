package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.Order;
import com.bookstore.bookstore_backend.exception.ResourceNotFoundException;
import com.bookstore.bookstore_backend.repository.OrderRepository;
import com.bookstore.bookstore_backend.entity.OrderItem;
import com.bookstore.bookstore_backend.repository.OrderItemRepository;
import com.bookstore.bookstore_backend.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Order addOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        // handle illigal orderItams
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }
        return orderRepository.save(savedOrder);
    }
    @Override
    public Order getOrderById(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with ID: " + order.getId())
        );

//        existingOrder.setUser(order.getUser() != null ? order.getUser() : existingOrder.getUser());
//        existingOrder.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : existingOrder.getOrderDate());
//        existingOrder.setTotalPrice(order.getTotalPrice() != null ? order.getTotalPrice() : existingOrder.getTotalPrice());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
}
