package com.example.BankCustomer.CustomerServiceImplementation;

import com.example.BankCustomer.CustomerRepository.IAccountRepository;
import com.example.BankCustomer.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplementation {

    @Autowired
    private IAccountRepository accountRepository;

    public ResponseEntity<?> getAllAccounts() {
        try {
            List<Account> accounts = accountRepository.findAll();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve accounts.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAccountById(Integer id) {
        try {
            Optional<Account> account = accountRepository.findById(id);
            if (account.isPresent()) {
                return new ResponseEntity<>(account.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve account.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> createAccount(Account account) {
        try {
            Account createdAccount = accountRepository.save(account);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create account.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateAccount(Account account) {
        try {
            Optional<Account> existingAccount = accountRepository.findById(account.getId());
            if (existingAccount.isPresent()) {
                Account updatedAccount = accountRepository.save(account);
                return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update account.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteAccountById(Integer id) {
        try {
            Optional<Account> existingAccount = accountRepository.findById(id);
            if (existingAccount.isPresent()) {
                accountRepository.deleteById(id);
                return new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete account.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public Account findByCustomerId(Integer customerId) {
        return accountRepository.findByCustomerCustomerId(customerId);
    }
}

