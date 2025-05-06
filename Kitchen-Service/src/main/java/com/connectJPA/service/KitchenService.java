package com.connectJPA.service;

import com.connectJPA.dto.request.OrderDetailEvent;
import com.connectJPA.dto.request.OrderEvent;
import com.connectJPA.entity.KitchenOrder;
import com.connectJPA.repository.KitchenOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenService {
    private final KitchenOrderRepository kitchenOrderRepository;
    private final KafkaTemplate<String, OrderDetailEvent> kafkaTemplate;

    public void processOrder(OrderEvent event) {
        for (OrderDetailEvent detailEvent : event.getOrderDetails()) {
//            KitchenOrder kitchenOrder = new KitchenOrder(
//                    detailEvent.getOrderDetailId(),
//                    detailEvent.getDish(),
//                    detailEvent.getQuantity(),
//                    "IN_PROGRESS"
//            );
            KitchenOrder kitchenOrder = new KitchenOrder();
            kitchenOrder.setDish(detailEvent.getDish());
            kitchenOrder.setQuantity(detailEvent.getQuantity());
            kitchenOrder.setStatus("IN_PROGRESS");


            kitchenOrderRepository.save(kitchenOrder);
            // Giả lập chế biến món ăn (5s)
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Cập nhật trạng thái hoàn thành
            kitchenOrder.setStatus("COMPLETED");
            kitchenOrderRepository.save(kitchenOrder);

            // Gửi sự kiện món ăn hoàn thành
            kafkaTemplate.send("order-detail-completed", detailEvent);
        }
    }
}
