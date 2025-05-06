package com.connectJPA.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long tableId;
    private Long userId;
    private List<OrderDetail> orderDetails;
    private String note;
}
