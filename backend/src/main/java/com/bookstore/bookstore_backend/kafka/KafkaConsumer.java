// package com.bookstore.bookstore_backend.kafka;

// import org.slf4j.LoggerFactory;
// import com.bookstore.bookstore_backend.service.OrderService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Component;
// import com.bookstore.bookstore_backend.entity.Order;
// import com.bookstore.bookstore_backend.utils.AppConstants;
// import com.bookstore.bookstore_backend.websocket.WebSocketServer;

// import lombok.AllArgsConstructor;

// import org.slf4j.Logger;

// @Component
// @AllArgsConstructor
// public class KafkaConsumer {

//     private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
//     @Autowired
//     private WebSocketServer ws;
//     @Autowired
//     private OrderService orderService;
//     @Autowired
//     private KafkaProducer kafkaProducer;

//     @KafkaListener(topics = AppConstants.ORDER_TOPIC, groupId = AppConstants.ORDER_GROUP)
//     public void listenOrderTopic(Order order) {
//         // LOGGER.info(String.format("\u001B[31m" + "Order started to process -> %s",
//         // order.toString() + "\u001B[0m"));
//         Order processedOrder = orderService.addOrder(order); // Process order
//         kafkaProducer.sendOrderResult(processedOrder);

//     }

//     @KafkaListener(topics = AppConstants.ORDER_RESULTS_TOPIC, groupId = AppConstants.ORDER_GROUP)
//     public void listenOrderResultTopic(Order order) {
//         try {
//             // String orderJson = objectMapper.writeValueAsString(order);
//             ws.sendMessageToUser(order.getUser().getId().toString(), "Order Processed--> " + order.getId());
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
