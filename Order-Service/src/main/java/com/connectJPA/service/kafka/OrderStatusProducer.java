package com.connectJPA.service.kafka;

import com.connectJPA.dto.event.OrderEventStatus;
import com.connectJPA.entity.Order;
import com.connectJPA.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusProducer {
    private final KafkaTemplate<String, Object> genericKafkaTemplate;
    private final OrderRepository orderRepository;

    public void sendOrderStatusToInvoice(OrderEventStatus orderEventStatus) {
        Order order = orderRepository.findById(orderEventStatus.getOrderId()).orElse(null);
        orderEventStatus.setOrderId(order.getOrderId());
        orderEventStatus.setStatus(orderEventStatus.getStatus());
        orderEventStatus.setTotalAmount(order.getTotalPrice());
        genericKafkaTemplate.send("order-status-topic", orderEventStatus);
    }
}
