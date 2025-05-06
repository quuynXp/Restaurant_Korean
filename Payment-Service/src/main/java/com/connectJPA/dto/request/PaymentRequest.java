package com.connectJPA.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    Long orderId;
    Double amount;
    String currency;
    String paymentMethod; // PAYPAL, VNPAY, COD, BANK_TRANSFER
    String description;
}
