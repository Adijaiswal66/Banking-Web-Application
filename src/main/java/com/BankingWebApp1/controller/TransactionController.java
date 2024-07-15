package com.BankingWebApp1.controller;

import com.BankingWebApp1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/transfer/{userId}/{beneficiaryId}")
    public ResponseEntity<String> transferMoney(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId, @RequestParam Double transferredAmount) {
        return this.transactionService.transferMoney(userId, beneficiaryId, transferredAmount);
    }

    @PostMapping(value = "/withdraw/{userId}")
    public ResponseEntity<String> withdrawMoney(@PathVariable("userId") Long userId, @RequestParam Double transferredAmount) {
        return this.transactionService.withdrawMoney(userId, transferredAmount);
    }

    @PostMapping(value = "/deposit/{userId}")
    public ResponseEntity<String> depositMoney(@PathVariable("userId") Long userId, @RequestParam Double transferredAmount) {
        return this.transactionService.depositMoney(userId, transferredAmount);
    }

    @GetMapping(value = "/balance/{userId}")
    public ResponseEntity<String> balanceEnquiry(@PathVariable("userId") Long userId) {
        return this.transactionService.balanceEnquiry(userId);
    }

}
