package com.BankingWebApp1.services;

import com.BankingWebApp1.entities.Beneficiaries;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BeneficiaryService {

    public ResponseEntity<String> addBeneficiaries(Long userId, Beneficiaries beneficiaries);

    public ResponseEntity<List<Beneficiaries>> getBeneficiaries(Long userId);
}
