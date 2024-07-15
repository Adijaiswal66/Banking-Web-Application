package com.BankingWebApp1.services;

import com.BankingWebApp1.dao.RoleRepository;
import com.BankingWebApp1.dao.UserRepository;
import com.BankingWebApp1.entities.Role;
import com.BankingWebApp1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public ResponseEntity<String> registerCustomer(User user) {
        Optional<User> existingUser = this.userRepository.getUserByEmail(user.getUserEmail());
        if (existingUser.isEmpty()) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                Role role = this.roleRepository.findById(501).get();

                user.getRoles().add(role);
                this.userRepository.save(user);
                return new ResponseEntity<>("Customer with email " + user.getUserEmail() + " is added successfully", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return new ResponseEntity<>("Customer with email " + user.getUserEmail() + " already exists!!", HttpStatus.ALREADY_REPORTED);
    }

    @Override
    public ResponseEntity<String> registerAdmin(User user) {
        Optional<User> existingUser = this.userRepository.getUserByEmail(user.getUserEmail());
        if (existingUser.isEmpty()) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                Role role = this.roleRepository.findById(502).get();
                user.getRoles().add(role);
                this.userRepository.save(user);
                return new ResponseEntity<>("Admin with email " + user.getUserEmail() + " is added successfully", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return new ResponseEntity<>("Admin with email " + user.getUserEmail() + " already exists!!", HttpStatus.ALREADY_REPORTED);
    }
}
