package com.example.BankCustomer.CustomerServiceImplementation;

import com.example.BankCustomer.CustomerRepository.IAccountRepository;
import com.example.BankCustomer.CustomerRepository.IBankTransactionRepository;
import com.example.BankCustomer.Entity.Account;
import com.example.BankCustomer.Entity.BankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankTransactionService {

    @Autowired
    private IBankTransactionRepository bankTransactionRepository;

    @Autowired
    private IAccountRepository accountRepository;

    public void deposit(BankTransaction transaction) throws Exception {
        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new Exception("Account not found"));

        if (account.getCustomer().getCustomerBankStatus().equalsIgnoreCase("DeActive")) {
            throw new Exception("Transaction not allowed. Account is deactivated.");
        }

        account.setAccountBalance(account.getAccountBalance() + transaction.getTransactionAmount());
        accountRepository.save(account);

        transaction.setTransactionType("DEPOSIT");
        bankTransactionRepository.save(transaction);
    }

    public void withdraw(BankTransaction transaction) throws Exception {
        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new Exception("Account not found"));

        if (account.getCustomer().getCustomerBankStatus().equalsIgnoreCase("DeActive")) {
            throw new Exception("Transaction not allowed. Account is deactivated.");
        }

        if (account.getAccountBalance() >= transaction.getTransactionAmount()) {
            account.setAccountBalance(account.getAccountBalance() - transaction.getTransactionAmount());
            accountRepository.save(account);

            transaction.setTransactionType("WITHDRAWAL");
            bankTransactionRepository.save(transaction);
        } else {
            throw new Exception("Insufficient funds");
        }
    }

    public void transfer(Account fromAccount, Account toAccount, Double amount) throws Exception {
        if (fromAccount.getCustomer().getCustomerBankStatus().equalsIgnoreCase("DeActive")) {
            throw new Exception("Transaction not allowed. Source account is deactivated.");
        }

        if (fromAccount.getAccountBalance() >= amount) {
            fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
            toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            BankTransaction transaction = new BankTransaction();
            transaction.setAccount(fromAccount);
            transaction.setTransactionAmount(amount);
            transaction.setTransactionType("TRANSFER");
            transaction.setRecipientAccount(toAccount);
            bankTransactionRepository.save(transaction);
        } else {
            throw new Exception("Insufficient funds in source account");
        }
    }
}
