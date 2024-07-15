package com.BankingWebApp1.services;

import com.BankingWebApp1.dao.BeneficiaryRepository;
import com.BankingWebApp1.dao.TransactionRepository;
import com.BankingWebApp1.dao.UserRepository;
import com.BankingWebApp1.entities.Beneficiaries;
import com.BankingWebApp1.entities.Transactions;
import com.BankingWebApp1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;


    @Override
    public ResponseEntity<String> transferMoney(Long userId, Long beneficiaryId, Double transferredAmount) {
        Optional<User> existingUser = this.userRepository.findById(userId);
        Optional<Beneficiaries> existingBeneficiary = this.beneficiaryRepository.findById(beneficiaryId);
        if (existingUser.isPresent() && existingBeneficiary.isPresent()) {

            Transactions transactions1 = new Transactions();
            existingUser.get().setAvailableBalance(existingUser.get().getAvailableBalance() - transferredAmount);
            transactions1.setFromAccountNumber(existingUser.get().getAccountNumber());
            transactions1.setToAccountNumber(existingBeneficiary.get().getAccountNumber());
            transactions1.setUser(this.userRepository.getReferenceById(userId));
            transactions1.setBeneficiaries(this.beneficiaryRepository.getReferenceById(beneficiaryId));
            transactions1.setTransactionDate(OffsetDateTime.now());
            transactions1.setTransferredAmount(transferredAmount);
            transactions1.setType("TRANSFER");
            this.transactionRepository.save(transactions1);
            return new ResponseEntity<>(transferredAmount + " Money Transferred successfully to account number: " + existingBeneficiary.get().getAccountNumber(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User or Beneficiary not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<String> withdrawMoney(Long userId, Double transferredAmount) {
        Optional<User> existingUser = this.userRepository.findById(userId);
        if (existingUser.isPresent()) {
            if (existingUser.get().getAvailableBalance() > transferredAmount) {
                Transactions transactions1 = new Transactions();
                existingUser.get().setAvailableBalance(existingUser.get().getAvailableBalance() - transferredAmount);
                transactions1.setFromAccountNumber(existingUser.get().getAccountNumber());
                transactions1.setUser(this.userRepository.getReferenceById(userId));
                transactions1.setTransactionDate(OffsetDateTime.now());
                transactions1.setTransferredAmount(transferredAmount);
                transactions1.setType("WITHDRAW");
                this.transactionRepository.save(transactions1);
                this.userRepository.save(existingUser.get());

                return new ResponseEntity<>("Withdrawal of money " + transferredAmount + " is successful!!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Insufficient balance", HttpStatus.FORBIDDEN);
            }

        }
        return new ResponseEntity<>(" User with id " + userId + " does not exists", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> depositMoney(Long userId, Double transferredAmount) {
        Optional<User> existingUser = this.userRepository.findById(userId);
        if (existingUser.isPresent()) {
            try {
                Transactions transactions1 = new Transactions();
                existingUser.get().setAvailableBalance(existingUser.get().getAvailableBalance() + transferredAmount);
                transactions1.setFromAccountNumber(existingUser.get().getAccountNumber());
                transactions1.setUser(this.userRepository.getReferenceById(userId));
                transactions1.setTransactionDate(OffsetDateTime.now());
                transactions1.setTransferredAmount(transferredAmount);
                transactions1.setType("DEPOSIT");
                this.transactionRepository.save(transactions1);
                this.userRepository.save(existingUser.get());
                return new ResponseEntity<>(transferredAmount + " has been deposited successfully to account number: " + transactions1.getFromAccountNumber(), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(" User with id " + userId + " does not exists", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> balanceEnquiry(Long userId) {
        Optional<User> existingUser = this.userRepository.findById(userId);
        if (existingUser.isPresent()) {
            try {
                Transactions transactions1 = new Transactions();
                existingUser.get().setAvailableBalance(existingUser.get().getAvailableBalance());
                transactions1.setFromAccountNumber(existingUser.get().getAccountNumber());
                transactions1.setUser(this.userRepository.getReferenceById(userId));
                transactions1.setTransactionDate(OffsetDateTime.now());
                transactions1.setType("BALANCE ENQUIRY");
                this.transactionRepository.save(transactions1);
                this.userRepository.save(existingUser.get());
                return new ResponseEntity<>("Available balance is " + existingUser.get().getAvailableBalance(), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(" User with id " + userId + " does not exists", HttpStatus.NOT_FOUND);

    }
}
