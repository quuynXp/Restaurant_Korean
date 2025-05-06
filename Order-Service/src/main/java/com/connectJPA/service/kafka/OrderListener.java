package com.connectJPA.service.kafka;

import com.connectJPA.dto.event.OrderDetailEvent;
import com.connectJPA.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderListener {
    private final OrderService orderService;

    @KafkaListener(topics = "order-detail-completed", groupId = "order-group")
    public void listenOrderDetailCompletion(OrderDetailEvent event) {

        System.out.println("Order detail completed: " + event.getOrderDetailId());
        orderService.completeOrderDetail(event.getOrderDetailId());
    }
}
