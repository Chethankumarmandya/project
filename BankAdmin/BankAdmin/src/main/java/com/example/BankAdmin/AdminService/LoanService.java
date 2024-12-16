package com.example.BankAdmin.AdminService;

import com.example.BankAdmin.Entity.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanService {
    @Autowired
    private RestTemplate restTemplate;

    public String approveOrDenyLoan(Integer customerId, Double loanAmount) {
        AccountDTO account = restTemplate.getForObject("http://localhost:8082/customer/account/" + customerId, AccountDTO.class);

        if (account.getAccountBalance() >= loanAmount) {
            return "Loan Approved";
        } else {
            return "Loan Denied due to insufficient balance";
        }
    }
}
