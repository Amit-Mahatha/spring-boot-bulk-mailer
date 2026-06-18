package com.send.bulk_mailer.dto;

import lombok.Data;

@Data
public class EmailRequest {

    private String from;
    private String to;
    private String subject;
    private String html;

}