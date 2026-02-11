package com.task1.scheduler.notification;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Content;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailNotificationService implements NotificationService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    @Override
    public void send(String to, String subject, String message) {
        try {
            Email from = new Email(fromEmail);
            Email toEmail = new Email(to);
            Content content = new Content("text/plain", message);
            Mail mail = new Mail(from, subject, toEmail, content);

            SendGrid sendGrid = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());


            Response response = sendGrid.api(request);

            System.out.println("SendGrid response code: " + response.getStatusCode());


        } catch (IOException e) {
            System.out.println("Email sending failed");
            e.printStackTrace();
        }
    }
}
