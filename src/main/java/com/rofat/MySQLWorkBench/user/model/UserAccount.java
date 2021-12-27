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
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int maId;                   //Master Account ID

    private int accountNumber;          //Account Number

    private AccountType accountType;    //Account Type

    private CurrencyType currencyType;  //Currency Type

    private double balance;             //Balance


}
