package com.example.BankCustomer.CustomerServiceImplementation;

import com.example.BankCustomer.CustomerRepository.ICustomerRepositoryImplementation;
import com.example.BankCustomer.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepositoryImplementation customerRepositoyImplementation;

    public Customer registerCustomer(Customer customer)
    {
        Customer customerSavedData = customerRepositoyImplementation.save(customer);
        return customerSavedData;
    }

    public String loginCustomer(String username,String password)
    {
        Optional<Customer> byCustomerName = customerRepositoyImplementation.findByCustomerName(username);
        Optional<Customer> byCustomerPassword = customerRepositoyImplementation.findByCustomerPassword(password);
        if(byCustomerName.isPresent() && byCustomerPassword.isPresent())
        {
            return "Login Successfully";
        }
        else {
            return "Invalid Credentials";
        }
    }

    public String updateCustomer(Customer customer) {
        Optional<Customer> existingCustomerOptional = customerRepositoyImplementation.findById(customer.getCustomerId());

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();

            // Update only the necessary fields
            existingCustomer.setCustomerEmail(customer.getCustomerEmail());
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setCustomerPassword(customer.getCustomerPassword());
            existingCustomer.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
            existingCustomer.setCustomerBankStatus(customer.getCustomerBankStatus());

            // Save the updated customer entity
            Customer updatedCustomer = customerRepositoyImplementation.save(existingCustomer);
            return updatedCustomer.toString();
        } else {
            return "No customer found for this particular ID";
        }
    }

    public String deleteCustomerById(Integer id) {
        try {
            Optional<Customer> customerOptional = customerRepositoyImplementation.findById(id);

            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();

                if ("DeActive".equalsIgnoreCase(customer.getCustomerBankStatus())) {
                    return "Customer account is already DeActivated";
                }

                // Update the customer's status to "DeActive"
                customer.setCustomerBankStatus("DeActive");
                customerRepositoyImplementation.save(customer);

                return "Customer DeActivated successfully";
            } else {
                return "Customer not found for this particular ID";
            }
        } catch (Exception e) {
            // Log the exception (this requires a logger to be set up)
            // logger.error("Error deactivating customer", e);
            return "An error occurred while trying to deactivate the customer: " + e.getMessage();
        }
    }

    public List<Customer> getCustomer()
    {
        List<Customer> allCustomers = customerRepositoyImplementation.findAll();
        if(allCustomers.isEmpty())
        {
            return null;
        }
        else return allCustomers;

    }

    public ResponseEntity<?> getCustomerById(Integer id) {
        try {
            Optional<Customer> customer = customerRepositoyImplementation.findById(id);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Customer not found for this particular ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
