package com.example.BankAdmin.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Integer id;
    private String accountNumber;
    private Double accountBalance;
    private Customer customer;
}
