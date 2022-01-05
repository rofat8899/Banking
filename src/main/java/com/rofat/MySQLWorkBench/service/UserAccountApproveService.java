package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;

public interface UserAccountApproveService {
    UserAccountEntity activateUserAccount(String userId, String userPin, int accountNumber);
    UserAccountApproveEntity addOrUpdateUserAccountApprove_(UserAccountApproveEntity userAccount);
}
