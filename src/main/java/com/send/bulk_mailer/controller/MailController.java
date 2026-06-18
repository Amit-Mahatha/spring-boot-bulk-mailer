package com.send.bulk_mailer.controller;

import com.send.bulk_mailer.dto.EmailRequest;
import com.send.bulk_mailer.model.User;
import com.send.bulk_mailer.service.CsvService;
import com.send.bulk_mailer.service.MailService;
import com.send.bulk_mailer.service.ResendService;
import com.send.bulk_mailer.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MailController {

    private final MailService mailService;
    private final CsvService csvService;
    private final TemplateService templateService;
    private final ResendService resendService;


    public MailController(
            MailService mailService,
            CsvService csvService,
            TemplateService templateService,
            ResendService resendService) {

        this.mailService = mailService;
        this.csvService = csvService;
        this.templateService = templateService;
        this.resendService = resendService;
    }

    @GetMapping("/hello")
    public String hello() {
        return mailService.getMessage();
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return csvService.getAllUsersFromDb();
    }

    @GetMapping("/eligible-users")
    public List<User> getEligibleUsers() {
        return csvService.getEligibleUsersFromDb();
    }

    @GetMapping("/preview-email")
    public String previewEmail() {

        List<User> eligibleUsers = csvService.getEligibleUsersFromDb();

        if (eligibleUsers.isEmpty()) {
            return "No eligible users found";
        }

        User user = eligibleUsers.get(0);

        return templateService.generateEmail(user);
    }

    @GetMapping("/send-test")
    public String sendTestEmail() {

        EmailRequest request = new EmailRequest();

        request.setFrom("onboarding@resend.dev");
        request.setTo("mahathaamitk@gmail.com");
        request.setSubject("Bulk Mailer Test");
        request.setHtml("<h1>Hello Amit</h1><p>First email from Spring Boot</p>");
        return resendService.sendEmail(request);
    }

    @GetMapping("/send-bulk")
    public String sendBulkEmails() {

        List<User> users =
                csvService.getPendingUsers();

        if (users.isEmpty()) {
            return "No eligible users found";
        }

        int successCount = 0;

        for (User user : users) {

            try {

                String html =
                        templateService.generateEmail(user);

                EmailRequest request =
                        new EmailRequest();

                request.setFrom("onboarding@resend.dev");
                request.setTo(user.getEmail());
                request.setSubject(
                        "Special offer for " +
                                user.getBrandName()
                );
                request.setHtml(html);

                resendService.sendEmail(request);
                csvService.markAsSent(user);

                successCount++;

            } catch (Exception e) {

                System.out.println(
                        "Failed to send email to: "
                                + user.getEmail()
                );
            }
        }

        return successCount +
                " emails sent successfully";
    }


}