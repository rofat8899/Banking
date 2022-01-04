package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImp implements UserAccountService {

    @Autowired
    private UserAccRepo userAccRepo;

    @Override
    public List<UserAccountEntity> getAllUserAccount() {
        return userAccRepo.findAll();
    }

    @Override
    public UserAccountEntity addUserAccount_(UserAccountEntity userAccount) {
        UserAccountEntity userAccount1 = userAccRepo.getUserAccountByAccountNumber(userAccount.getAccountNumber());

        if (userAccount1 != null) {
            userAccount.setId(userAccount1.getId());
        }
        return userAccRepo.save(userAccount);
    }

    @Override
    public List<UserAccountEntity> getUserAccountByMasterAccId(Integer id) {
        return userAccRepo.getUserAccountByMaId(id);
    }

    @Override
    public UserAccountEntity getUserAccountByAccountNumberAndMaId(Integer AccountNumber, Integer maId) {
        return userAccRepo.getUserAccountByAccountNumberAndMaId(AccountNumber, maId);
    }

    @Override
    public void deleteUserAccount(Integer accountNumber) {

        userAccRepo.deleteUserAccountByAccountNumber(accountNumber);
    }
}
