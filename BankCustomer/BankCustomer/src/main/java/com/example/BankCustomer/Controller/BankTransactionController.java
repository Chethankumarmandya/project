package com.example.BankCustomer.Controller;

import com.example.BankCustomer.CustomerServiceImplementation.BankTransactionService;
import com.example.BankCustomer.Entity.BankTransaction;
import com.example.BankCustomer.TransferRequestDTO.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody BankTransaction transaction) {
        try {
            bankTransactionService.deposit(transaction);
            return new ResponseEntity<>("Deposit successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Deposit failed: " + e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody BankTransaction transaction) {
        try {
            bankTransactionService.withdraw(transaction);
            return new ResponseEntity<>("Withdrawal successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Withdrawal failed: " + e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        try {
            bankTransactionService.transfer(transferRequest.getFromAccount(), transferRequest.getToAccount(), transferRequest.getAmount());
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Transfer failed: " + e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
