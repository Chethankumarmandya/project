package com.example.BankCustomer.Controller;

import com.example.BankCustomer.CustomerRepository.ICustomerRepositoryImplementation;
import com.example.BankCustomer.CustomerServiceImplementation.CustomerService;
import com.example.BankCustomer.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ICustomerRepositoryImplementation customerRepositoryImplementation;

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        try {
            Customer customerRegister = customerService.registerCustomer(customer);
            return new ResponseEntity<>(customerRegister, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/loginCustomer/{username}/{password}")
//    public ResponseEntity<String> loginCustomer(@PathVariable("username") String username, @PathVariable("password") String password) {
//        try {
//            String customerLogin = customerService.loginCustomer(username, password);
//            if (customerLogin.equals("Login Successfully")) {
////                Optional<Customer> c = customerRepositoryImplementation.findByCustomerName(username);
////                System.out.println(c);
//                return new ResponseEntity<>(customerLogin, HttpStatus.OK);
////                System.out.println(c);
//            } else {
//                return new ResponseEntity<>(customerLogin, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
@PostMapping("/loginCustomer/{username}/{password}")
public ResponseEntity<?> loginCustomer(@PathVariable("username") String username, @PathVariable("password") String password) {
    try {
        String customerLogin = customerService.loginCustomer(username, password);
        if (customerLogin.equals("Login Successfully")) {
            Optional<Customer> customer = customerRepositoryImplementation.findByCustomerName(username);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);  // Return customer details
            } else {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(customerLogin, HttpStatus.UNAUTHORIZED);
        }
    } catch (Exception e) {
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        try {
            String customerUpdate = customerService.updateCustomer(customer);
            if (customerUpdate.equals("No customer found for this particular Name")) {
                return new ResponseEntity<>("Invalid Customer", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customerUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        try {
            List<Customer> customers = customerService.getCustomer();
            return new ResponseEntity<>(customers, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/customer/deactivate/{id}")
    public ResponseEntity<String> deactivateCustomerById(@PathVariable("id") Integer id) {
        try {
            String result = customerService.deleteCustomerById(id);

            switch (result) {
                case "Customer DeActivated successfully":
                    return new ResponseEntity<>(result, HttpStatus.OK);
                case "Customer account is already DeActivated":
                    return new ResponseEntity<>(result, HttpStatus.CONFLICT);
                case "Customer not found for this particular ID":
                    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Log the exception for debugging
            // logger.error("Error deactivating customer", e);
            return new ResponseEntity<>("Internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
