package com.BankingWebApp1.controller;


import com.BankingWebApp1.dao.UserRepository;
import com.BankingWebApp1.entities.Transactions;
import com.BankingWebApp1.entities.User;
import com.BankingWebApp1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@EnableMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PutMapping(value = "customer/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") long userId, @RequestBody User user) {
        return this.userService.updateUser(userId, user);
    }

    @GetMapping(value = "customer/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {
        return this.userService.getUserById(userId);
    }

    @GetMapping(value = "customer/beneficiary/{userId}")
    public ResponseEntity<User> getAllBeneficiaries(@PathVariable("userId") long userId) {
        return this.userService.getAllBeneficiaries(userId);
    }

    @GetMapping(value = "/transaction/{userId}")
    public ResponseEntity<List<Transactions>> getTransactionsByUserId(@PathVariable("userId") long userId) {
        return this.userService.getTransactionsByUserId(userId);
    }

    // Admin

    @PutMapping(value = "admin/{userId}")
    public ResponseEntity<String> updateAdmin(@PathVariable long userId, @RequestBody User user) {
        return this.userService.updateAdmin(userId, user);
    }

    @GetMapping(value = "admin/customers")
    public ResponseEntity<List<User>> getAllCustomer() {
        return this.userService.getAllCustomer();
    }

    @GetMapping(value = "/admin/{userId}")
    public ResponseEntity<User> findByIdForAdmin(@PathVariable long userId) {
        return this.userService.findByIdForAdmin(userId);
    }
    @GetMapping(value = "admin/transactions")
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        return this.userService.getAllTransactions();
    }


}
