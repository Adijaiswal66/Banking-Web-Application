package com.BankingWebApp1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Beneficiaries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long beneficiaryId;

    private String firstName;
    private String lastName;
    private Long accountNumber;
    private String bankName;
    private Long maxTransferLimit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_user_id")
    @JsonBackReference
    private User user;
}
