package com.connectJPA.service.kafka;

import com.connectJPA.dto.event.OrderEvent;
import com.connectJPA.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderStatusUpdate(Order order) {
        OrderEvent event = new OrderEvent(order.getOrderId(), order.getStatus().name());
        kafkaTemplate.send("order-status-topic", event);
    }
}
