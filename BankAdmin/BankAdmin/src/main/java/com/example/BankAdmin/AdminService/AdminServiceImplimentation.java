package com.example.BankAdmin.AdminService;

import com.example.BankAdmin.AdminRepository.IAdminRepository;
import com.example.BankAdmin.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImplimentation {

    @Autowired
    private IAdminRepository adminRepository;


    public Admin registerAdmin(Admin admin)
    {
        Admin createdAdmin = adminRepository.save(admin);
        return createdAdmin;
    }


    public String loginAdmin(String username,String password) {
        Optional<Admin> byAdminUserName = adminRepository.findByAdminUserName(username);

        if (byAdminUserName.isPresent()) {
            if((byAdminUserName.get().getAdminUserName().equals(username)) && (byAdminUserName.get().getAdminPassword().equals(password)))
            {
                return "Admin is Login Successfully";
            }
            else return "Invalid credentials";
        }
        return "Not registered";
    }

}
