package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;

public interface UserAccountApproveService {
    UserAccountDTO activateUserAccount(String userId, String userPin, int accountNumber);
    UserAccountApproveEntity addOrUpdateUserAccountApprove_(UserAccountApproveEntity userAccount);
}
