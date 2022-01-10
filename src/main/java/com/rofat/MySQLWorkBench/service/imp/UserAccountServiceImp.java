package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountServiceImp implements UserAccountService {

    @Autowired
    private UserAccRepo userAccRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<UserAccountDTO> getAllUserAccount()
    {
        List<UserAccountDTO> userAccountDTOList= new ArrayList<>();
        List<UserAccountEntity> userAccountEntities = userAccRepo.findAll();
        for(UserAccountEntity each: userAccountEntities)
        {
            userAccountDTOList.add(new UserAccountDTO(each));
        }
        return userAccountDTOList;
    }

    @Override
    public UserAccountDTO addUserAccount_(UserAccountEntity userAccount) {
        UserAccountEntity userAccount1 = userAccRepo.getUserAccountByAccountNumber(userAccount.getAccountNumber());

        if (userAccount1 != null) {
            userAccount.setId(userAccount1.getId());
        }
        return new UserAccountDTO(userAccRepo.save(userAccount)) ;
    }

    @Override
    public UserAccountDTO getDefaultAccountByMasterAccId(int maId) {
        UserEntity user = userRepo.getUserByMaId(maId);
        return new UserAccountDTO(userAccRepo.getUserAccountByAccountNumberAndMaId(user.getDefaultAccount(),user.getMaId()));
    }

    @Override
    public List<UserAccountDTO> getUserAccountByMasterAccId(Integer id) {
        List<UserAccountDTO> userAccountDTOList= new ArrayList<>();
        List<UserAccountEntity> userAccountEntity = userAccRepo.getUserAccountByMaId(id);
        for(UserAccountEntity each : userAccountEntity)
        {
            userAccountDTOList.add(new UserAccountDTO(each));
        }
        return userAccountDTOList;
    }

    @Override
    public UserAccountDTO getUserAccountByAccountNumberAndMaId(Integer AccountNumber, Integer maId) {
        return new UserAccountDTO(userAccRepo.getUserAccountByAccountNumberAndMaId(AccountNumber, maId));
    }

    @Override
    public UserAccountDTO getUserAccountByAccountNumber(Integer accountNumber) {
        return new UserAccountDTO(userAccRepo.getUserAccountByAccountNumber(accountNumber));
    }

    @Override
    public void deleteUserAccount(Integer accountNumber) {

        userAccRepo.deleteUserAccountByAccountNumber(accountNumber);
    }
}
