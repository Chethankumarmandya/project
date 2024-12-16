package com.example.BankCustomer.CustomerServiceImplementation;


import com.example.BankCustomer.CustomerRepository.IAccountRepository;
import com.example.BankCustomer.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void updateAccountBalance(Integer accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setAccountBalance(account.getAccountBalance() + amount);

        if (account.getAccountBalance() == 0) {

            restTemplate.postForObject("http://localhost:8081/admin/notifyZeroBalance", account, Void.class);
        }

        accountRepository.save(account);
    }
}
