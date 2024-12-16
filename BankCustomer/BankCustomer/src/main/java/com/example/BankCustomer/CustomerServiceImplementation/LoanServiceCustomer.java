package com.example.BankCustomer.CustomerServiceImplementation;

import com.example.BankCustomer.CustomerRepository.ICustomerRepositoryImplementation;
import com.example.BankCustomer.CustomerRepository.ILoanRepository;
import com.example.BankCustomer.Entity.Loan;
import com.example.BankCustomer.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceCustomer {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private ICustomerRepositoryImplementation customerRepository;

    public List<Loan> getUnapprovedLoans() {
        return loanRepository.findByApprovedFalse();
    }

    public Loan approveLoan(Integer loanId, boolean approved) throws Exception {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setApproved(approved);
            return loanRepository.save(loan);
        } else {
            throw new Exception("Loan not found");
        }
    }
    public Loan applyForLoan(Loan loan, Integer customerId) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            loan.setCustomer(customerOptional.get());
            return loanRepository.save(loan);
        } else {
            throw new Exception("Customer not found");
        }
    }
}
