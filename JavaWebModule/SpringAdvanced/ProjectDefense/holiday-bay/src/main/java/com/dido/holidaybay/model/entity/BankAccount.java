package com.dido.holidaybay.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="bank_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BankAccount extends BaseEntity {

    @Column(name="amount")
    private double amount;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity user;

}
