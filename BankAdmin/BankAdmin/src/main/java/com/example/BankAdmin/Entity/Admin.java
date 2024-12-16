package com.example.BankAdmin.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(nullable = false, unique = true)
    private String adminUserName;

    @Column(nullable = false, unique = true)
    private String adminEmail;

    @Column(nullable = false, unique = true)
    private String adminPassword;
}
