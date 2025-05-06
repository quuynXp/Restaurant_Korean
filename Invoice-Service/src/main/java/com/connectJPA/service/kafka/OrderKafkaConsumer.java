package com.connectJPA.service.kafka;

import com.connectJPA.dto.event.OrderEventStatus;
import com.connectJPA.entity.OrderStatus;
import com.connectJPA.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaConsumer {
    private final InvoiceService invoiceService;

    @KafkaListener(topics = "order-status-topic", groupId = "invoice-group")
    public void consumeOrderEvent(OrderEventStatus event) {
        if (OrderStatus.COMPLETED.equals(event.getStatus())) {
            invoiceService.createInvoice(event.getOrderId(), event.getTotalAmount());
        }
    }
}
