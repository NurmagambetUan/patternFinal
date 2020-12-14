package com.example.pattern.models.entities;

import com.example.pattern.models.audit.AuditModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_banks",
        initialValue = 1,
        allocationSize = 1
)

public class Bank extends AuditModel {
    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_holder")
    private String accountHolder;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "type")
    private String type;

    @Column(name = "interest")
    private Double interest;


    private Boolean invested;
    private Boolean confirmed;
    private Boolean completed;
}

