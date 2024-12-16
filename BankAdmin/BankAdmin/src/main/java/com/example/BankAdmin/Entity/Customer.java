package com.example.BankAdmin.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer customerId;

    private String customerName;


    private String customerEmail;


    private String customerPassword;


    private String customerPhoneNumber;

    private String customerBankStatus;
}
