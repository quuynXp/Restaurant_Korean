package com.connectJPA.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherEmailRequest {
    private String to;
    private String customerName;
    private String voucherCode;
}
