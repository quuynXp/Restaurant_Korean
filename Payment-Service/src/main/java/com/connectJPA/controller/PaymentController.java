package com.connectJPA.controller;

import com.connectJPA.dto.request.PaymentRequest;
import com.connectJPA.dto.response.PaymentResponse;
import com.connectJPA.service.DirectPaymentService;
import com.connectJPA.service.PaypalPaymentService;
import com.connectJPA.service.VNPayPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaypalPaymentService paypalService;
    private final VNPayPaymentService vnpayService;
    private final DirectPaymentService directService;

    public PaymentController(PaypalPaymentService paypalService, VNPayPaymentService vnpayService, DirectPaymentService directService) {
        this.paypalService = paypalService;
        this.vnpayService = vnpayService;
        this.directService = directService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        switch (request.getPaymentMethod().toUpperCase()) {
            case "PAYPAL":
                return ResponseEntity.ok(paypalService.createPayment(request));
            case "VNPAY":
                return ResponseEntity.ok(vnpayService.createPayment(request));
            case "COD":
            case "BANK_TRANSFER":
                return ResponseEntity.ok(directService.createPayment(request));
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }
    }

    @GetMapping("/execute")
    public ResponseEntity<PaymentResponse> executePayment(
            @RequestParam Long paymentId,
            @RequestParam Long payerId,
            @RequestParam String paymentMethod) {

        if ("PAYPAL".equalsIgnoreCase(paymentMethod)) {
            return ResponseEntity.ok(paypalService.executePayment(paymentId, payerId));
        } else {
            throw new IllegalArgumentException("Unsupported operation for this method");
        }
    }
}

