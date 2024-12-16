package com.example.BankCustomer.Controller;

import com.example.BankCustomer.CustomerServiceImplementation.LoanServiceCustomer;
import com.example.BankCustomer.Entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanServiceCustomer loanService;

    @GetMapping("/unapproved")
    public ResponseEntity<List<Loan>> getUnapprovedLoans() {
        try {
            List<Loan> loans = loanService.getUnapprovedLoans();
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/approve/{loanId}/{approved}")
    public ResponseEntity<String> approveLoan(@PathVariable Integer loanId, @PathVariable boolean approved) {
        try {
            loanService.approveLoan(loanId, approved);
            return new ResponseEntity<>("Loan status updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating loan status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/apply/{customerId}")
    public ResponseEntity<String> applyForLoan(@RequestBody Loan loan, @PathVariable Integer customerId) {
        try {
            loanService.applyForLoan(loan, customerId);
            return new ResponseEntity<>("Loan application successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Loan application failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
