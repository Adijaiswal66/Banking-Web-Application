package com.BankingWebApp1.controller;

import com.BankingWebApp1.entities.Beneficiaries;
import com.BankingWebApp1.services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping(value = "/{userId}")
    public ResponseEntity<String> addBeneficiaries(@PathVariable("userId") Long userId, @RequestBody Beneficiaries beneficiaries){
        return this.beneficiaryService.addBeneficiaries(userId, beneficiaries);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Beneficiaries>> getBeneficiaries(@PathVariable("userId") Long userId){
        return this.beneficiaryService.getBeneficiaries(userId);
    }
}
