package com.example.BankAdmin.AdminController;



import com.example.BankAdmin.Entity.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        try {

            AccountDTO accountDTO1 = restTemplate.postForObject("http://localhost:8082/customer/account/create", accountDTO, AccountDTO.class);

            return new ResponseEntity<>(accountDTO1, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO) {
        try {
            restTemplate.put("http://localhost:8082/customer/account/update", accountDTO);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
        try {
            restTemplate.delete("http://localhost:8082/customer/account/delete/" + id);
            return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        try {
            ResponseEntity<AccountDTO[]> response = restTemplate.getForEntity("http://localhost:8082/customer/account/all", AccountDTO[].class);
            List<AccountDTO> accounts = List.of(response.getBody());
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Integer id) {
        try {
            ResponseEntity<AccountDTO> response = restTemplate.getForEntity("http://localhost:8082/customer/account/" + id, AccountDTO.class);

            if (response.getStatusCode() == HttpStatus.NOT_FOUND || response.getBody() == null) {
                return new ResponseEntity<>("Account not found for ID: " + id, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("Account not found for ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve account.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

