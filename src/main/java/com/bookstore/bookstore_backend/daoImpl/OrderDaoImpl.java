package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.Order;
import com.bookstore.bookstore_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.OrderDao;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }
}
