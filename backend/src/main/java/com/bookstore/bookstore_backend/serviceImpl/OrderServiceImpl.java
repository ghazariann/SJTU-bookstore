package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.dao.OrderDao;
import com.bookstore.bookstore_backend.dao.OrderItemDao;
import com.bookstore.bookstore_backend.entity.Order;
import com.bookstore.bookstore_backend.exception.ResourceNotFoundException;
import com.bookstore.bookstore_backend.entity.OrderItem;
import com.bookstore.bookstore_backend.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor // generates a constructor that accepts all final fields as arguments
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Order addOrder(Order order) {

        double totalPrice = 0.0;
        
    

        // for (OrderItem orderItem : order.getOrderItems()) {

        //     Map<String, Double> requestPayload = new HashMap<>();
        //     requestPayload.put("price", orderItem.getPrice());
        //     requestPayload.put("quantity", (double) orderItem.getQuantity());

        //     Double itemTotalPrice = restTemplate.postForObject("http://localhost:8082/bookPriceCalculator",
        //             requestPayload,
        //             Double.class);
        //     if (itemTotalPrice != null) {
        //         totalPrice += itemTotalPrice;
        //     }
        // }
        for (OrderItem orderItem : order.getOrderItems()) {
            double itemPrice = orderItem.getPrice();
            int itemQuantity = orderItem.getQuantity();
        
            totalPrice += itemPrice * itemQuantity;
        }
        order.setTotalPrice(totalPrice);

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
