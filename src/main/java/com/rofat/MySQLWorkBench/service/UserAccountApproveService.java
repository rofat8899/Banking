package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;

import java.util.Map;

public interface UserAccountApproveService {
    UserAccountDTO activateUserAccount(Map<String,Object> obj, int accountNumber);
    UserAccountApproveEntity addOrUpdateUserAccountApprove_(UserAccountApproveEntity userAccount);
}
