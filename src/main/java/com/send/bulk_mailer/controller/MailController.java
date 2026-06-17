package com.send.bulk_mailer.controller;

import com.send.bulk_mailer.model.User;
import com.send.bulk_mailer.service.CsvService;
import com.send.bulk_mailer.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MailController {

    private final MailService mailService;
    private final CsvService csvService;

    public MailController(MailService mailService,
                          CsvService csvService) {

        this.mailService = mailService;
        this.csvService = csvService;
    }

    @GetMapping("/hello")
    public String hello() {
        return mailService.getMessage();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return csvService.readUsers();
    }
}