package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;

import java.util.List;

public interface UserAccountService {

    List<UserAccountDTO> getAllUserAccount();

    List<UserAccountDTO> getUserAccountByMasterAccId(Integer id);

    UserAccountDTO getUserAccountByAccountNumberAndMaId(Integer AccountNumber, Integer maId);

    UserAccountDTO getUserAccountByAccountNumber(Integer accountNumber);

    UserAccountDTO addUserAccount_(UserAccountEntity userAccount);

    UserAccountDTO getDefaultAccountByMasterAccId(int maId);

    void deleteUserAccount(Integer accountId);
}
