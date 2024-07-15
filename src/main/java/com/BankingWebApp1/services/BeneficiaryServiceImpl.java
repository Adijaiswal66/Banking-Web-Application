package com.BankingWebApp1.services;

import com.BankingWebApp1.dao.BeneficiaryRepository;
import com.BankingWebApp1.dao.UserRepository;
import com.BankingWebApp1.entities.Beneficiaries;
import com.BankingWebApp1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> addBeneficiaries(Long userId, Beneficiaries beneficiaries) {
        Optional<User> existingUser = this.userRepository.findById(userId);
        if (existingUser.isPresent()) {
            try {
                beneficiaries.setUser(existingUser.get());
                this.beneficiaryRepository.save(beneficiaries);
                return new ResponseEntity<>("Beneficiary with id " + beneficiaries.getBeneficiaryId() + " is added successfully to user with id " + userId, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        return new ResponseEntity<>("User with id " + userId + " does not exist!!", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Beneficiaries>> getBeneficiaries(Long userId) {
        List<Beneficiaries> list = this.beneficiaryRepository.findAll();
        if (!list.isEmpty()){
            try{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
