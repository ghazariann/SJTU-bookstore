package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.OrderItem;
import com.bookstore.bookstore_backend.repository.OrderItemRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.OrderItemDao;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    private final OrderItemRepository orderItemRepository;

    public OrderItemDaoImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem findById(long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        orderItemRepository.deleteById(id);
    }
}
