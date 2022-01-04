package com.rofat.MySQLWorkBench.model;

import com.rofat.MySQLWorkBench.constant.AccountType;
import com.rofat.MySQLWorkBench.constant.CurrencyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "USER_ACCOUNT")
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public UserAccountEntity(int id, int accountNumber, AccountType accountType, double balance, CurrencyType currencyType, int maId) {
        this.id = id;
        this.accountNumber =accountNumber;
        this.accountType= accountType;
        this.balance= balance;
        this.currencyType= currencyType;
        this.maId=maId;
    }
}
