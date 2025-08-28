package com.example.ecom.adapters;

import org.springframework.stereotype.Service;

import com.example.ecom.libraries.Sendgrid;

@Service
public class SendgridAdapter implements EmailAdapter {

    private final Sendgrid sendgrid;

    public SendgridAdapter() {
        this.sendgrid = new Sendgrid();
    }

    @Override
    public void sendEmail(String email, String subject, String body) {
        sendgrid.sendEmailAsync(email, subject, body);
    }
}
