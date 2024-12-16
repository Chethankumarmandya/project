package com.example.BankAdmin.AdminRepository;

import com.example.BankAdmin.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByAdminUserName(String name);



}
