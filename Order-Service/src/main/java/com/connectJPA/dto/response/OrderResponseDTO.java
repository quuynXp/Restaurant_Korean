package com.connectJPA.dto.response;

import com.connectJPA.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private Long tableId;
    private Integer tableNumber;
    private List<OrderItemDTO> items;
    private String note;
    private OrderStatus status;
    private LocalDate createdAt;
}
