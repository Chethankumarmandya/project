package com.example.BankCustomer.Controller;


import com.example.BankCustomer.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/account")
public class AccountControllerImplementation {

    @Autowired
    private com.example.BankCustomer.CustomerServiceImplementation.AccountServiceImplementation accountServiceImplementation;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        return accountServiceImplementation.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable("id") Integer id) {
        return accountServiceImplementation.getAccountById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {

        return accountServiceImplementation.createAccount(account);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {
        return accountServiceImplementation.updateAccount(account);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Integer id) {
        return accountServiceImplementation.deleteAccountById(id);
    }
    @GetMapping("/get/{customerId}")
    public ResponseEntity<?> getAccountByCustomerId(@PathVariable Integer customerId) {
        Account account = accountServiceImplementation.findByCustomerId(customerId);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not created", HttpStatus.OK);
        }
    }
}
