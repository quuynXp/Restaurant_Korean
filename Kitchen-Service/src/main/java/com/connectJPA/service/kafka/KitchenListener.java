package com.connectJPA.service.kafka;

import com.connectJPA.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenListener {
    private final KitchenService kitchenService;

    @KafkaListener(topics = "kitchen-orders", groupId = "kitchen-group")
    public void listenOrder(OrderEvent event) {
        System.out.println("Kitchen received order: " + event);
        kitchenService.processOrder(event);
    }
}
