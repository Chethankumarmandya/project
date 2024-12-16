package com.example.BankCustomer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Double rateOfInterest;

    @Column(nullable = false)
    private int tenureMonths;

    @Column(nullable = false)
    private boolean approved;  // New field to track loan approval status

    @ManyToOne
    @JoinColumn
    private Customer customer;
}
