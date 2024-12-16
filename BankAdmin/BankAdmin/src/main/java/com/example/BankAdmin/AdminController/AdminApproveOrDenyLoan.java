package com.example.BankAdmin.AdminController;

import com.example.BankAdmin.AdminService.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminApproveOrDenyLoan {
    @Autowired
    private LoanService loanService;

    @PostMapping("/loan/approve")
    public ResponseEntity<String> approveOrDenyLoan(@RequestParam Integer customerId, @RequestParam Double loanAmount) {
        try {
            String result = loanService.approveOrDenyLoan(customerId, loanAmount);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while processing loan approval", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

