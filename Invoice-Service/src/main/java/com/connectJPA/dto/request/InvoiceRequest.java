package com.connectJPA.dto.request;

import lombok.Data;

@Data
public class InvoiceRequest {
    private Long orderId;
    private Double totalAmount;
}
