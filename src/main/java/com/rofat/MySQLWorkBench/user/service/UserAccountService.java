package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.model.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> getAllUserAccount();

    UserAccount addUserAccount_(UserAccount userAccount);

    List<UserAccount> getUserAccountByMasterAccId(Integer id);

    UserAccount getUserAccountByAccountNumberAndMaId(Integer AccountNumber,Integer maId);

    void deleteUserAccount(Integer accountId);
}
