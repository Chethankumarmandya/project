package com.example.BankCustomer.CustomerRepository;

import com.example.BankCustomer.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepositoryImplementation extends JpaRepository<Customer,Integer> {


    Optional<Customer> findByCustomerName(String name);
    Optional<Customer> findByCustomerPassword(String name);

}
