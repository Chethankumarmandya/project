package com.example.BankCustomer.CustomerRepository;

import com.example.BankCustomer.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



    @Repository
    public interface IAccountRepository extends JpaRepository<Account, Integer> {
//        Account findByAccountNumber(String accountNumber);
        Account findByCustomerCustomerId(Integer customerId);
    }