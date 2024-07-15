package com.BankingWebApp1.dao;

import com.BankingWebApp1.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
