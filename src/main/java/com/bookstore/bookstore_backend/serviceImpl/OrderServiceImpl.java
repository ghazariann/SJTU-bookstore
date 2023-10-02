package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.dao.OrderDao;
import com.bookstore.bookstore_backend.dao.OrderItemDao;
import com.bookstore.bookstore_backend.entity.Order;
import com.bookstore.bookstore_backend.exception.ResourceNotFoundException;
import com.bookstore.bookstore_backend.entity.OrderItem;
import com.bookstore.bookstore_backend.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // generates a constructor that accepts all final fields as arguments
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;

    @Transactional
    public Order addOrder(Order order) {
        Order savedOrder = orderDao.save(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(savedOrder);
            orderItemDao.save(orderItem);
        }
        return savedOrder;
    }

    @Override
    public Order getOrderById(long id) {
        Optional<Order> optionalOrder = orderDao.findById(id);
        return optionalOrder.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }

    @Override
    public List<Order> listOrders() {
        return orderDao.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
        Order existingOrder = orderDao.findById(order.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with ID: " + order.getId()));
        return orderDao.save(existingOrder);
    }

    @Override
    public void deleteOrder(long id) {
        orderDao.deleteById(id);
    }
}
