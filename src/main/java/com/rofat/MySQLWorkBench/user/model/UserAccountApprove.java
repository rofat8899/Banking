package com.rofat.MySQLWorkBench.user.model;

import com.rofat.MySQLWorkBench.user.constant.AccountType;
import com.rofat.MySQLWorkBench.user.constant.CurrencyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAccountApprove {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int maId;                   //Master Account ID

    private int accountNumber;          //Account Number

    private AccountType accountType;    //Account Type

    private CurrencyType currencyType;  //Currency Type

    private double balance;             //Balance

    private boolean pending;
}
