package com.BankingWebApp1.services;

import com.BankingWebApp1.dao.TransactionRepository;
import com.BankingWebApp1.dao.UserRepository;
import com.BankingWebApp1.entities.Transactions;
import com.BankingWebApp1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<String> updateUser(long userId, User user) {
        if (this.userRepository.existsById(userId)) {
            try {
                this.userRepository.findById(userId)
                        .map(u -> {
                            u.setUserEmail(user.getUserEmail());
                            u.setPassword(user.getPassword());
                            u.setFirstName(user.getFirstName());
                            u.setLastName(user.getLastName());
                            u.setPhoneNumber(user.getPhoneNumber());
                            u.setAddress(user.getAddress());
                            return userRepository.save(u);
                        });
                return new ResponseEntity<>("User with id " + userId + " is updated successfully", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return new ResponseEntity<>("Unable to find user with id " + userId, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<User> getUserById(long userId) {
        Optional<User> existingUser = this.userRepository.getUserById(userId);
        if (existingUser.isPresent()) {
            this.userRepository.getUserById(userId);
            return new ResponseEntity<>(existingUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<User> getAllBeneficiaries(@PathVariable("userId") long userId){
        Optional<User> existingCustomer = this.userRepository.getUserById(userId);
        if (existingCustomer.isPresent()) {
            this.userRepository.getUserById(userId);
            return new ResponseEntity<>(existingCustomer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<List<Transactions>> getTransactionsByUserId(long userId) {
        Optional<User> existingUser = this.userRepository.getUserById(userId);
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(existingUser.get().getTransactions(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Admin

    @Override
    public ResponseEntity<String> updateAdmin(long userId, User user) {
        if (this.userRepository.existsById(userId)) {
            this.userRepository.findById(userId)
                    .map(u -> {
                        u.setUserEmail(user.getUserEmail());
                        u.setPassword(user.getPassword());
                        u.setFirstName(user.getFirstName());
                        u.setLastName(user.getLastName());
//                        u.setRole("ADMIN");
                        return userRepository.save(u);
                    });
            return new ResponseEntity<>("User with id " + userId + " is updated successfully", HttpStatus.OK);

        } else {
            return new ResponseEntity<>(" Unable to find user with id " + userId, HttpStatus.NOT_FOUND);
        }
    }
    

    @Override
    public ResponseEntity<List<User>> getAllCustomer() {
        List<User> list = this.userRepository.findAll();
        if (list.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<User> findByIdForAdmin(long userId) {
        Optional<User> existingUser = this.userRepository.findByIdForAdmin(userId);
        if (existingUser.isPresent()) {
            this.userRepository.findByIdForAdmin(userId);
            return new ResponseEntity<>(existingUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    
    @Override
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> list = this.transactionRepository.findAll();
        System.out.println(list);
        if (list.size() >= 0){
            return new ResponseEntity<>(list,HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }


    }


}
