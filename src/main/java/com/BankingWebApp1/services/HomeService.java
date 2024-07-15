package com.BankingWebApp1.services;

import com.BankingWebApp1.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface HomeService {

    public ResponseEntity<String> registerCustomer(@RequestBody User user);
    public ResponseEntity<String> registerAdmin(@RequestBody User user);

}
