package com.BankingWebApp1.dao;

import com.BankingWebApp1.entities.Beneficiaries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiaries, Long> {
}
