package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.merchant.model.MerchantSettlements;
import com.rofat.MySQLWorkBench.merchant.repo.MerchantSettlementRepo;
import com.rofat.MySQLWorkBench.user.Repo.UserAccApproveRepo;
import com.rofat.MySQLWorkBench.user.Repo.UserAccRepo;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
import com.rofat.MySQLWorkBench.user.model.UserAccountApprove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountApproveServiceImp implements UserAccountApproveService{

    @Autowired
    private UserAccApproveRepo userAccApproveRepo;

    @Autowired
    private UserAccRepo userAccRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired

    private MerchantSettlementRepo merchantSettlementRepo;

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
            updateUserAccountApprove(userAccountApprove);
                // Add Account Settlement to Merchant
            UserAccount userAccount1 = userAccRepo.getUserAccountByAccountNumber(userAccountApprove.getAccountNumber());
            insertMerchantSettlement(userAccountApprove.getMaId(),userAccount1.getId());
                //print
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


    @Override
    public void insertMerchantSettlement(int mmid, int sid) {

        merchantSettlementRepo.save(new MerchantSettlements(mmid,sid));
    }

    public void updateUserAccountApprove(UserAccountApprove userAccountApprove)
    {
        userAccountApprove.setPending(false);
        userAccApproveRepo.save(userAccountApprove);
    }


}
