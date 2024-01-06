// package com.bookstore.bookstore_backend.kafka;

// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.support.KafkaHeaders;
// import org.springframework.messaging.Message;
// import org.springframework.messaging.support.MessageBuilder;
// import org.springframework.stereotype.Service;
// import com.bookstore.bookstore_backend.entity.Order;
// import com.bookstore.bookstore_backend.utils.AppConstants;

// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class KafkaProducer {

//     // private static final Logger LOGGER =
//     // LoggerFactory.getLogger(KafkaProducer.class);

//     private KafkaTemplate<String, Order> kafkaTemplate;

//     public void sendOrder(Order order) {
//         // LOGGER.info(String.format("Order sent -> %s", order.toString()));
//         Message<Order> message = MessageBuilder
//                 .withPayload(order)
//                 .setHeader(KafkaHeaders.TOPIC, AppConstants.ORDER_TOPIC)
//                 .build();
//         kafkaTemplate.send(message);
//     }

//     public void sendOrderResult(Order processedOrder) {
//         Message<Order> message = MessageBuilder
//                 .withPayload(processedOrder)
//                 .setHeader(KafkaHeaders.TOPIC, AppConstants.ORDER_RESULTS_TOPIC)
//                 .build();
//         kafkaTemplate.send(message);
//     }
// }