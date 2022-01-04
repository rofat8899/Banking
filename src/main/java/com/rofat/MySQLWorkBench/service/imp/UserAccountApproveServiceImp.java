package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.model.MerchantSettlementsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.repository.MerchantSettlementRepo;
import com.rofat.MySQLWorkBench.repository.UserAccApproveRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.UserAccountApproveService;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import com.rofat.MySQLWorkBench.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountApproveServiceImp implements UserAccountApproveService {

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
    public UserAccountApproveEntity addOrUpdateUserAccountApprove_(UserAccountApproveEntity userAccountApproveEntity) {
        UserAccountApproveEntity userAccountApproveEntity1 = userAccApproveRepo.getUserAccountByAccountNumber(userAccountApproveEntity.getAccountNumber());
        userAccountApproveEntity.setPending(true);
        if (userAccountApproveEntity1 != null) {
            userAccountApproveEntity.setId(userAccountApproveEntity1.getId());

        }
        return userAccApproveRepo.save(userAccountApproveEntity);
    }

    @Override
    public UserAccountEntity activateUserAccount(int userId, String userPin, int accountNumber) {

        Boolean validate = userService.validation(userId, userPin);
        Boolean isAdmin = userService.isAdmin(userId);
        if (validate) {
            if (isAdmin) {
                UserAccountApproveEntity userAccountApproveEntity = userAccApproveRepo.getUserAccountByAccountNumber(accountNumber);
                UserAccountEntity userAccountEntity = new UserAccountEntity(userAccountApproveEntity.getId(), userAccountApproveEntity.getAccountNumber(),
                        userAccountApproveEntity.getAccountType(), userAccountApproveEntity.getBalance(),
                        userAccountApproveEntity.getCurrencyType(), userAccountApproveEntity.getMaId());
                userAccountService.addUserAccount_(userAccountEntity);
                updateUserAccountApprove(userAccountApproveEntity);
                // Add Account Settlement to Merchant
                UserAccountEntity userAccountEntity1 = userAccRepo.getUserAccountByAccountNumber(userAccountApproveEntity.getAccountNumber());
                insertMerchantSettlement(userAccountApproveEntity.getMaId(), userAccountEntity1.getId());
                //print
                System.out.format("Account %d is activated\n", accountNumber);
                return userAccountEntity;
            } else {
                System.out.println("You are not an admin");
            }
        }
        return null;
    }


    @Override
    public void insertMerchantSettlement(int mid, int sid) {

        merchantSettlementRepo.save(new MerchantSettlementsEntity(mid, sid));
    }

    public void updateUserAccountApprove(UserAccountApproveEntity userAccountApproveEntity) {
        userAccountApproveEntity.setPending(false);
        userAccApproveRepo.save(userAccountApproveEntity);
    }


}
