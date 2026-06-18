package com.send.bulk_mailer.service;

import com.send.bulk_mailer.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResendService {

    @Value("${resend.api.key}")
    private String apiKey;

    private static final String RESEND_URL =
            "https://api.resend.com/emails";

    public String sendEmail(EmailRequest request) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EmailRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        RESEND_URL,
                        entity,
                        String.class
                );

        return response.getBody();
    }
}