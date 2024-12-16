package com.example.BankCustomer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column(nullable = false)
    private String transactionType; // e.g., "DEPOSIT", "WITHDRAWAL", "TRANSFER"

    @Column(nullable = false)
    private Double transactionAmount;

    @ManyToOne
    @JoinColumn
    private Account account;

    @ManyToOne
    @JoinColumn
    private Account recipientAccount; // Nullable, only for fund transfers
}
