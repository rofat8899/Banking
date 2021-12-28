package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.model.UserAccount;
import com.rofat.MySQLWorkBench.user.model.UserAccountApprove;

public interface UserAccountApproveService {
    UserAccountApprove addOrUpdateUserAccountApprove_(UserAccountApprove userAccount);

    UserAccount activateUserAccount(int userId, String userPin, String userRole, int accountNumber);
}
