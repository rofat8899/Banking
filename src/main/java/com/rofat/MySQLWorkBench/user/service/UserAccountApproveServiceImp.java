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

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public UserAccountApprove addOrUpdateUserAccountApprove_(UserAccountApprove userAccountApprove) {
        UserAccountApprove userAccountApprove1= userAccApproveRepo.getUserAccountByAccountNumber(userAccountApprove.getAccountNumber());
        userAccountApprove.setPending(true);
        if(userAccountApprove1!=null)
        {
            userAccountApprove.setId(userAccountApprove1.getId());

        }
            return userAccApproveRepo.save(userAccountApprove);
    }

    @Override
    public UserAccount activateUserAccount(int userId, String userPin,  int accountNumber) {

        Boolean validate = userService.validation(userId,userPin);
        Boolean isAdmin = userService.isAdmin(userId);
        if(validate)
        {
            if(isAdmin){
            UserAccountApprove userAccountApprove = userAccApproveRepo.getUserAccountByAccountNumber(accountNumber);
            UserAccount userAccount = new UserAccount(userAccountApprove.getId(),userAccountApprove.getAccountNumber(),
                    userAccountApprove.getAccountType(),userAccountApprove.getBalance(),
                    userAccountApprove.getCurrencyType(),userAccountApprove.getMaId());
            userAccountService.addUserAccount_(userAccount);
            deleteUserAccountApprove(userAccountApprove);
            System.out.format("Account %d is activated\n",accountNumber);
            return userAccount;
            }
            else
            {
                System.out.println("You are not an admin");
            }
        }
        return null;
    }

    public void deleteUserAccountApprove(UserAccountApprove userAccountApprove){
        userAccApproveRepo.delete(userAccountApprove);
    }
}
