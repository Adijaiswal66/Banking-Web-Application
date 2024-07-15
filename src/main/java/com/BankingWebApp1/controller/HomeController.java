package com.BankingWebApp1.controller;

import com.BankingWebApp1.entities.User;
import com.BankingWebApp1.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @PostMapping(value = "/registerCustomer")
    public ResponseEntity<String> registerCustomer(@RequestBody User user) {
        return this.homeService.registerCustomer(user);
    }

    @PostMapping(value = "/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody User user) {
        return this.homeService.registerAdmin(user);
    }


}
