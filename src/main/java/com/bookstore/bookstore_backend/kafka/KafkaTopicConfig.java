// package com.bookstore.bookstore_backend.kafka;

// import org.apache.kafka.clients.admin.NewTopic;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Conditional;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.config.TopicBuilder;

// import com.bookstore.bookstore_backend.utils.AppConstants;

// @Configuration
// // @Conditional(KafkaCondition.class)
// public class KafkaTopicConfig {

//     @Bean
//     public NewTopic orderTopic() {
//         return TopicBuilder.name(AppConstants.ORDER_TOPIC)
//                 .partitions(10)
//                 .replicas(1)
//                 .build();
//     }

//     @Bean
//     public NewTopic orderResultsTopic() {
//         return TopicBuilder.name(AppConstants.ORDER_RESULTS_TOPIC)
//                 .partitions(10)
//                 .replicas(1)
//                 .build();
//     }
// }
