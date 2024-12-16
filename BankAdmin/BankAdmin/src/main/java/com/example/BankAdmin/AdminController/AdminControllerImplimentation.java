package com.example.BankAdmin.AdminController;

import com.example.BankAdmin.AdminService.AdminServiceImplimentation;
import com.example.BankAdmin.Entity.Admin;
import com.example.BankAdmin.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminControllerImplimentation {

    @Autowired
    private AdminServiceImplimentation adminService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/registerAdmin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        System.out.println(admin);
        try {
            Admin createdAdmin = adminService.registerAdmin(admin);
            if (createdAdmin != null) {
                return new ResponseEntity<>(createdAdmin, HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/loginAdmin/{username}/{password}")
    public ResponseEntity<String> loginAdmin(@PathVariable("username") String username , @PathVariable("password") String password)
    {
        try {

        String loginDetails = adminService.loginAdmin(username, password);

        if(loginDetails.equals("Admin is Login Successfully"))
        {
            return new ResponseEntity<>(loginDetails,HttpStatus.OK);
        }
        else if(loginDetails.equals("Invalid credentials"))
        {
            return new ResponseEntity<>(loginDetails,HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(loginDetails,HttpStatus.NOT_FOUND);
        }
    }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerCustomerFromAdmin")
    public ResponseEntity<Customer> registerCustomerFromAdmin(@RequestBody Customer customer)
    { try {
        Customer forObject = restTemplate.postForObject("http://localhost:8082/registerCustomer", customer, Customer.class);
        return new ResponseEntity<>(forObject, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @PostMapping("/updateCustomerFromAdmin")
    public ResponseEntity<ResponseEntity<String>> updateCustomerFromAdmin(@RequestBody Customer customer) {
        try {
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8082/updateCustomer", customer, String.class);
            return new ResponseEntity<>(stringResponseEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//@PutMapping("/updateCustomerFromAdmin")
//public ResponseEntity<String> updateCustomerFromAdmin(@RequestBody Customer customer) {
//    try {
//        // Making the PUT request to the customer service to update customer details
//        restTemplate.put("http://localhost:8082/updateCustomer", customer);
//
//        // Return success message
//        return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
//    } catch (Exception e) {
//        return new ResponseEntity<>("Failed to update customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}



    @GetMapping("/getCustomersFromAdmin")
    public ResponseEntity<List<Customer>> getCustomerFromAdmin() {
        try {
            ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/getAllCustomers", Customer[].class);
            Customer[] customersArray = responseEntity.getBody();
            List<Customer> customersList = Arrays.asList(customersArray);
            return ResponseEntity.ok(customersList);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deactivateCustomer/{userId}")
    public ResponseEntity<String> deactivateCustomerFromAdmin(@PathVariable("userId") Integer userId) {
        try {
            restTemplate.delete("http://localhost:8082/customer/deactivate/" + userId);
            return new ResponseEntity<>("Customer with ID " + userId + " deactivated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/CustomerById/{userId}")
    public ResponseEntity<?> getCustomerByIdFromAdmin(@PathVariable("userId") Integer userId) {
        try {
            ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:8082/customer" + userId, Customer.class);

            if (response.getStatusCode() == HttpStatus.NOT_FOUND || response.getBody() == null) {
                return new ResponseEntity<>("Customer not found for ID: " + userId, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("Customer not found for ID: " + userId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

