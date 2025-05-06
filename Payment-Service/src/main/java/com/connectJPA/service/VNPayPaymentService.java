package com.connectJPA.service;

import com.connectJPA.dto.request.PaymentRequest;
import com.connectJPA.dto.response.PaymentResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class VNPayPaymentService implements PaymentService {

    @Value("${vnpay.tmnCode}")
    private String tmnCode;

    @Value("${vnpay.hashSecret}")
    private String hashSecret;

    @Value("${vnpay.payUrl}")
    private String payUrl;

    @Value("${vnpay.returnUrl}")
    private String returnUrl;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        try {
            Map<String, String> vnpParams = new HashMap<>();
            vnpParams.put("vnp_Version", "2.1.0");
            vnpParams.put("vnp_Command", "pay");
            vnpParams.put("vnp_TmnCode", tmnCode);
            vnpParams.put("vnp_Amount", String.valueOf(request.getAmount().intValue() * 100)); // nhân 100
            vnpParams.put("vnp_CurrCode", "VND");
            vnpParams.put("vnp_TxnRef", request.getOrderId());
            vnpParams.put("vnp_OrderInfo", request.getDescription());
            vnpParams.put("vnp_Locale", "vn");
            vnpParams.put("vnp_ReturnUrl", returnUrl);
            vnpParams.put("vnp_IpAddr", "127.0.0.1");
            vnpParams.put("vnp_CreateDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

            List<String> fieldNames = new ArrayList<>(vnpParams.keySet());
            Collections.sort(fieldNames);

            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            for (String fieldName : fieldNames) {
                String value = vnpParams.get(fieldName);
                if ((value != null) && (value.length() > 0)) {
                    hashData.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
                    query.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
                }
            }

            String queryUrl = query.toString();
            queryUrl = queryUrl.substring(0, queryUrl.length() - 1);

            String vnp_SecureHash = hmacSHA512(hashSecret, hashData.toString().substring(0, hashData.length() - 1));
            String fullUrl = payUrl + "?" + queryUrl + "&vnp_SecureHash=" + vnp_SecureHash;

            return PaymentResponse.builder()
                    .paymentUrl(fullUrl)
                    .paymentId(request.getOrderId())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("VNPay create payment failed", e);
        }
    }

    @Override
    public PaymentResponse executePayment(Long paymentId, Long payerId) {
        // VNPay không cần execute. Thành công thì sẽ redirect về returnUrl.
        return PaymentResponse.builder()
                .paymentId(paymentId)
                .build();
    }

    private String hmacSHA512(String key, String data) throws Exception {
        Mac hmac512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac512.init(secretKey);
        byte[] bytes = hmac512.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }
}

