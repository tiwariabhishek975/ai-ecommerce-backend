package com.example.aiecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    // Must be placed inside the class body, with the imports above
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "ecommerce-events", groupId = "ecommerce-group")
    public void consume(String message) {
        log.info("Received Kafka Event: {}", message);
    }
}