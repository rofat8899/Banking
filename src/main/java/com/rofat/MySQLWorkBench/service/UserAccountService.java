package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.UserAccountEntity;

import java.util.List;

public interface UserAccountService {

    List<UserAccountEntity> getAllUserAccount();

    List<UserAccountEntity> getUserAccountByMasterAccId(Integer id);

    UserAccountEntity getUserAccountByAccountNumberAndMaId(Integer AccountNumber, Integer maId);

    UserAccountEntity getUserAccountByAccountNumber(Integer accountNumber);

    UserAccountEntity addUserAccount_(UserAccountEntity userAccount);

    void deleteUserAccount(Integer accountId);
}
