package com.rofat.MySQLWorkBench.model;

import com.rofat.MySQLWorkBench.constant.AccountType;
import com.rofat.MySQLWorkBench.constant.CurrencyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_ACCOUNT_APPROVE")
public class UserAccountApproveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    @Column(name="MASTER_ACCOUNT_ID")
    private int maId;                   //Master Account ID
    @Column(name="ACCOUNT_NUMBER")
    private int accountNumber;          //Account Number
    @Column(name="ACCOUNT_TYPE")
    private AccountType accountType;    //Account Type
    @Column(name="CURRENCY_TYPE")
    private CurrencyType currencyType;  //Currency Type
    @Column(name="BALANCE")
    private double balance;             //Balance
    @Column(name="PENDING")
    private boolean pending;
}
