package com.connectJPA.dto.event;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventStatus {
    private Long orderId;
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

