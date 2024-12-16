package com.example.BankAdmin.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendZeroBalanceNotification(String customerEmail, String customerName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("masteradmin@bank.com");
        message.setSubject("Zero Balance Alert");
        message.setText("Customer " + customerName + " with email " + customerEmail + " has a zero balance.");
        mailSender.send(message);
    }
}
