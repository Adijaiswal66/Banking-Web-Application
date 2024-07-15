package com.BankingWebApp1.services;

import com.BankingWebApp1.entities.Transactions;
import com.BankingWebApp1.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    public ResponseEntity<String> updateUser(@PathVariable("userId") long userId, @RequestBody User user);

    public ResponseEntity<User> getUserById(@PathVariable("userId") long userId);

    public ResponseEntity<User> getAllBeneficiaries(@PathVariable("userId") long userId);

    public ResponseEntity<List<Transactions>> getTransactionsByUserId(@PathVariable("customerId") long customerId);

    public ResponseEntity<String> updateAdmin(@PathVariable long userId, @RequestBody User user);

    public ResponseEntity<List<User>> getAllCustomer();

    public ResponseEntity<List<Transactions>> getAllTransactions();

    public ResponseEntity<User> findByIdForAdmin(long customerId);

}
