package com.BankingWebApp1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private Double transferredAmount;
    private OffsetDateTime transactionDate;
    private String type;
    //Transfer/Withdraw/Deposit/KYC/Verification/Balance Enquiry

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiary_beneficiary_id")
//    @JsonBackReference
    private Beneficiaries beneficiaries;
}
