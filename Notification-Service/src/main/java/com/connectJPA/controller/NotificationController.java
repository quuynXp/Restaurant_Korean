package com.connectJPA.controller;

import com.connectJPA.dto.request.EmailRequest;
import com.connectJPA.dto.request.VoucherEmailRequest;
import com.connectJPA.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/registration")
    public String sendRegistrationMail(@RequestBody EmailRequest request) {
        emailService.sendSimpleMail(request);
        return "Email đã được gửi.";
    }

    @PostMapping("/voucher")
    public String sendVoucherMail(@RequestBody VoucherEmailRequest request) {
        emailService.sendVoucherMail(request);
        return "Email voucher đã được gửi.";
    }
}

