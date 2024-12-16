package com.example.BankCustomer.Controller;

import com.example.BankCustomer.CustomerServiceImplementation.LoanEMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanEMIController {

    @Autowired
    private LoanEMIService loanEMIService;

    @GetMapping("/loan/emi")
    public ResponseEntity<Double> calculateEMI(@RequestParam double loanAmount, @RequestParam double rateOfInterest, @RequestParam int numberOfMonths) {
        try {
            double emi = loanEMIService.calculateEMI(loanAmount, rateOfInterest, numberOfMonths);
            return ResponseEntity.ok(emi);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
