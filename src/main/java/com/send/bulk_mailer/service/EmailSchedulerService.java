package com.send.bulk_mailer.service;

import com.send.bulk_mailer.controller.MailController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailSchedulerService {

    private final MailController mailController;

    public EmailSchedulerService(
            MailController mailController) {

        this.mailController = mailController;
    }

    @Scheduled(fixedRateString = "${scheduler.interval}")

    public void runScheduler() {

        System.out.println(
                "Scheduler started..."
        );

        String result =
                mailController.sendBulkEmails();

        System.out.println(result);
    }
}