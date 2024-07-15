package com.BankingWebApp1.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface TransactionService {

    public ResponseEntity<String> transferMoney(@PathVariable("userId") Long userId, @PathVariable("beneficiaryId") Long beneficiaryId, @RequestParam Double transferredAmount);

    public ResponseEntity<String> withdrawMoney(@PathVariable("userId") Long userId, @RequestParam Double transferredAmount);

    public ResponseEntity<String> depositMoney(@PathVariable("userId") Long userId, @RequestParam Double transferredAmount);

    public ResponseEntity<String> balanceEnquiry(@PathVariable("userId") Long userId);
}
