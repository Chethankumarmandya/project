package com.example.BankCustomer.CustomerRepository;

import com.example.BankCustomer.Entity.Account;
import com.example.BankCustomer.Entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBankTransactionRepository extends JpaRepository<BankTransaction, Integer> {

    // Find all transactions by account
    List<BankTransaction> findByAccount(Account account);

    // Find all transactions by transaction type (e.g., "DEPOSIT", "WITHDRAWAL", "TRANSFER")
    List<BankTransaction> findByTransactionType(String transactionType);
}

