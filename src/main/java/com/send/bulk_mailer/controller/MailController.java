package com.send.bulk_mailer.controller;

import com.send.bulk_mailer.model.User;
import com.send.bulk_mailer.service.CsvService;
import com.send.bulk_mailer.service.MailService;
import com.send.bulk_mailer.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MailController {

    private final MailService mailService;
    private final CsvService csvService;
    private final TemplateService templateService;

    public MailController(
            MailService mailService,
            CsvService csvService,
            TemplateService templateService) {

        this.mailService = mailService;
        this.csvService = csvService;
        this.templateService = templateService;
    }

    @GetMapping("/hello")
    public String hello() {
        return mailService.getMessage();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return csvService.readUsers();
    }

    @GetMapping("/eligible-users")
    public List<User> getEligibleUsers() {
        return csvService.getEligibleUsers();
    }

    @GetMapping("/preview-email")
    public String previewEmail() {

        User user =
                csvService
                        .getEligibleUsers()
                        .get(0);

        return templateService.generateEmail(user);
    }
}