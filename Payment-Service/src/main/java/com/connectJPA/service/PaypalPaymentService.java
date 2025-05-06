package com.connectJPA.service;

import com.connectJPA.dto.request.PaymentRequest;
import com.connectJPA.dto.response.PaymentResponse;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaypalPaymentService implements PaymentService {

    @Value("${paypal.client.id}")
    String clientId;

    @Value("${paypal.client.secret}")
    String clientSecret;

    @Value("${paypal.mode}")
    String mode; // sandbox or live

    APIContext apiContext;

    @PostConstruct
    public void init() {
        apiContext = new APIContext(clientId, clientSecret, mode);
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        try {
            Payment payment = getPayment(request);

            Payment createdPayment = payment.create(apiContext);

            String approvalUrl = createdPayment.getLinks().stream()
                    .filter(link -> link.getRel().equals("approval_url"))
                    .findFirst()
                    .map(Links::getHref)
                    .orElseThrow(() -> new RuntimeException("No approval URL found"));

            return PaymentResponse.builder()
                    .paymentId(createdPayment.getId())
                    .paymentUrl(approvalUrl)
                    .build();

        } catch (PayPalRESTException e) {
            throw new RuntimeException("PayPal create payment failed", e);
        }
    }

    private static Payment getPayment(PaymentRequest request) {
        Amount amount = new Amount();
        amount.setCurrency(request.getCurrency());
        amount.setTotal(String.format("%.2f", request.getAmount()));

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = Collections.singletonList(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://yourdomain.com/payment/cancel");
        redirectUrls.setReturnUrl("https://yourdomain.com/payment/success");
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    @Override
    public PaymentResponse executePayment(Long paymentId, Long payerId) {
        try {
            Payment payment = new Payment();
            payment.setId(paymentId);
            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);
            Payment executedPayment = payment.execute(apiContext, paymentExecution);
            return PaymentResponse.builder()
                    .paymentId(executedPayment.getId())
                    .paymentUrl(null)
                    .build();
        } catch (PayPalRESTException e) {
            throw new RuntimeException("PayPal execute payment failed", e);
        }
    }
}

