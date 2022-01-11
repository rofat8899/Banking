package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountBalanceDTO {
    private CurrencyType currencyType;
    private int accountNumber;
    private double balance;
    public AccountBalanceDTO(UserAccountEntity userAccountEntity){
        this.accountNumber=userAccountEntity.getAccountNumber();
        this.currencyType=userAccountEntity.getCurrencyType();
        this.balance=userAccountEntity.getBalance();
    }
}
