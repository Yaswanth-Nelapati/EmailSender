package com.example.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MailController {

    @Autowired
    private JavaMailSender mailSender;
    @CrossOrigin(origins = "*")
    @PostMapping("/send-mail")
    public ResponseEntity<String> sendMail(@RequestBody Map<String, String> request) {
        String toEmail = request.get("email");

        // You can customize this message
        String subject = "Hello from Spring Boot!";
        String body = "This is a real email sent from a Spring Boot application.";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("nelapaticyashh@gmail.com"); // Replace with your email

            mailSender.send(message);

            return ResponseEntity.ok("Mail sent to " + toEmail);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mail failed: " + e.getMessage());
        }
    }
}

