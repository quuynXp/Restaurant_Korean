package com.connectJPA.service;

import com.connectJPA.dto.request.PaymentRequest;
import com.connectJPA.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse executePayment(Long paymentId, Long payerId);
}

