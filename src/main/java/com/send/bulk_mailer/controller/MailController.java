package com.send.bulk_mailer.controller;

import com.send.bulk_mailer.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/hello")
    public String hello() {

        return mailService.getMessage();
    }
}