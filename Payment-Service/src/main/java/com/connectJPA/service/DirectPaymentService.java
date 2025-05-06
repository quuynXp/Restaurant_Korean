package com.connectJPA.service;

import com.connectJPA.dto.request.PaymentRequest;
import com.connectJPA.dto.response.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class DirectPaymentService implements PaymentService {

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        return PaymentResponse.builder()
                .paymentId(request.getOrderId()) // ID đơn hàng
                .paymentUrl(null)
                .build();
    }

    @Override
    public PaymentResponse executePayment(Long paymentId, Long payerId) {
        return PaymentResponse.builder()
                .paymentId(paymentId)
                .build();
    }
}

