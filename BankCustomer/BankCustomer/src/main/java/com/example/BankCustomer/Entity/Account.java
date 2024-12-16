package com.example.BankCustomer.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Double accountBalance;

    @OneToOne
    @JoinColumn
    private Customer customer;

}
