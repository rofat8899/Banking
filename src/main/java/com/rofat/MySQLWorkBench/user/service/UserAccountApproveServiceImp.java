package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.Repo.UserAccApproveRepo;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
import com.rofat.MySQLWorkBench.user.model.UserAccountApprove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountApproveServiceImp implements UserAccountApproveService{

    @Autowired
    private UserAccApproveRepo userAccApproveRepo;

    @Autowired
    private UserService userService;

    @Override
    public UserAccountApprove addOrUpdateUserAccountApprove_(UserAccountApprove userAccountApprove) {
        UserAccountApprove userAccountApprove1= userAccApproveRepo.getUserAccountByAccountNumber(userAccountApprove.getAccountNumber());
        userAccountApprove.setId(userAccountApprove1.getId());
        return userAccApproveRepo.save(userAccountApprove);
    }

    @Override
    public UserAccount activateUserAccount(int userId, String userPin, String userRole, int accountNumber) {
        Boolean validate = userService.validation(userId,userPin);
        if(validate)
        {

        }


        return null;
    }
}
