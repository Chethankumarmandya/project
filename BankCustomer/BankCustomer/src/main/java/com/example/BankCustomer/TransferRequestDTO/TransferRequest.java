package com.example.BankCustomer.TransferRequestDTO;

import com.example.BankCustomer.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private Account fromAccount;
    private Account toAccount;
    private Double amount;
}
