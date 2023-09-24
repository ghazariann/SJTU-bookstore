package com.bookstore.bookstore_backend.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.bookstore.bookstore_backend.entity.Order;
import com.bookstore.bookstore_backend.utils.AppConstants;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrder(Order order) {
        // LOGGER.info(String.format("Order sent -> %s", order.toString()));
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, AppConstants.ORDER_TOPIC)
                .build();
        kafkaTemplate.send(message);
    }

    public void sendOrderResult(Order processedOrder) {
        // LOGGER.info(String.format("Order result sent -> %s",
        // processedOrder.toString()));
        Message<Order> message = MessageBuilder
                .withPayload(processedOrder)
                .setHeader(KafkaHeaders.TOPIC, AppConstants.ORDER_RESULTS_TOPIC)
                .build();
        kafkaTemplate.send(message);
    }
}