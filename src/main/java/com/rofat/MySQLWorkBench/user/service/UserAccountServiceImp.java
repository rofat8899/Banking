package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.Repo.UserAccRepo;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAccountServiceImp implements UserAccountService {

    @Autowired
    private UserAccRepo userAccRepo;

    @Override
    public List<UserAccount> getAllUserAccount() {
        return userAccRepo.findAll();
    }

    @Override
    public UserAccount addUserAccount_(UserAccount userAccount) {
        UserAccount userAccount1= userAccRepo.getUserAccountByAccountNumber(userAccount.getAccountNumber());
        userAccount.setId(userAccount1.getId());
        return userAccRepo.save(userAccount);
    }

    @Override
    public List<UserAccount> getUserAccountByMasterAccId(Integer id) {
        return userAccRepo.getUserAccountByMaId(id);
    }
}
