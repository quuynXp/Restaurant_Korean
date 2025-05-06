package com.connectJPA.service;

import com.connectJPA.dto.request.EmailRequest;
import com.connectJPA.dto.request.VoucherEmailRequest;

public interface EmailService {
    void sendSimpleMail(EmailRequest request);
    void sendVoucherMail(VoucherEmailRequest request);
}
