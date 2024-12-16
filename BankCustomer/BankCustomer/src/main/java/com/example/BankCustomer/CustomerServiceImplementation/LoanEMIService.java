package com.example.BankCustomer.CustomerServiceImplementation;

import org.springframework.stereotype.Service;

@Service
public class LoanEMIService {

    public double calculateEMI(double loanAmount, double rateOfInterest, int numberOfMonths) {
        double monthlyInterestRate = rateOfInterest / (12 * 100);
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonths)) /
                (Math.pow(1 + monthlyInterestRate, numberOfMonths) - 1);
        return emi;
    }
}
