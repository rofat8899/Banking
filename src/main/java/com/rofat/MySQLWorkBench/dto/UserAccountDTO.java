package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserAccountDTO {
    private int id;
    private CurrencyType currencyType;
    private int accountNumber;
    UserAccountDTO(UserAccountEntity userAccountEntity){
        this.id= userAccountEntity.getId();
        this.currencyType=userAccountEntity.getCurrencyType();
        this.accountNumber=userAccountEntity.getAccountNumber();
    }
    void addEntity(UserAccountEntity userAccountEntity){
        this.id= userAccountEntity.getId();
        this.currencyType=userAccountEntity.getCurrencyType();
        this.accountNumber=userAccountEntity.getAccountNumber();
    }
}
