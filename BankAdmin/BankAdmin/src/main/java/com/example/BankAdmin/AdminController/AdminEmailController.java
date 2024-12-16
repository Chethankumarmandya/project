package com.example.BankAdmin.AdminController;

import com.example.BankAdmin.AdminService.EmailService;
import com.example.BankAdmin.Entity.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminEmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/notifyZeroBalance")
    public ResponseEntity<String> notifyZeroBalance(@RequestBody AccountDTO account) {
        try {
            String customerEmail = account.getCustomer().getCustomerEmail();
            String customerName = account.getCustomer().getCustomerName();
            emailService.sendZeroBalanceNotification(customerEmail, customerName);
            return new ResponseEntity<>("Notification sent successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send notification.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
