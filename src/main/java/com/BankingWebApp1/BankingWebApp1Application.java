package com.BankingWebApp1;

import com.BankingWebApp1.dao.RoleRepository;
import com.BankingWebApp1.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BankingWebApp1Application implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BankingWebApp1Application.class, args);
        System.out.println("Application is running...");
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role role = new Role();
            role.setRoleId(501);
            role.setName("ROLE_CUSTOMER");

            Role role1 = new Role();
            role1.setRoleId(502);
            role1.setName("ROLE_ADMIN");

            List<Role> roles = List.of(role, role1);

            List<Role> result = this.roleRepository.saveAll(roles);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
