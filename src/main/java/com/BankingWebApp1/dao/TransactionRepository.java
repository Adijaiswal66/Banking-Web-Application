package com.BankingWebApp1.dao;

import com.BankingWebApp1.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {

}
