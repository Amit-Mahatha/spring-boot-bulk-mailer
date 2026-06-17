package com.send.bulk_mailer.service;

import com.send.bulk_mailer.model.User;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class TemplateService {

    public String loadTemplate() {

        try {

            InputStream inputStream =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream("templates/mailer-template.html");

            if (inputStream == null) {
                throw new RuntimeException("Template not found");
            }

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load template",
                    e
            );
        }
    }

    public String generateEmail(User user) {

        String template = loadTemplate();

        template = template.replace(
                "{{userName}}",
                user.getName()
        );

        template = template.replace(
                "{{brandName}}",
                user.getBrandName()
        );

        return template;
    }
}