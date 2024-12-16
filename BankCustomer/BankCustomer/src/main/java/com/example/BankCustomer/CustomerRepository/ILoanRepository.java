package com.example.BankCustomer.CustomerRepository;

import com.example.BankCustomer.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByApprovedFalse();  // Fetch only unapproved loans
}
