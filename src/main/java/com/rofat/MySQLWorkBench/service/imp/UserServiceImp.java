package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.constant.Role;
import com.rofat.MySQLWorkBench.exception.BadRequestException;
import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.repository.PinRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import com.rofat.MySQLWorkBench.service.UserService;
import lombok.AllArgsConstructor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final PinRepo pinRepo;

    @Autowired
    private final UserAccountService userAccountService;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        Boolean existsUser = userRepo.existsByUserId(user.getUserId());
        if (existsUser) {
            throw new BadRequestException("User existed");
        }
        return userRepo.save(user);
    }

    @Override
    public UserEntity getUserByMasterAccId(Integer id) {
        return userRepo.getUserByMaId(id);
    }

    @Override
    public UserEntity getUserByUserId(Integer id) {
        return userRepo.getUserByUserId(id);
    }

    @Override
    public Boolean validation(Integer id, String pin) {
        Boolean existsUser = userRepo.existsByUserId(id); //check if database containing user or not
        PinEntity pinEntity1 = pinRepo.findPinByUserId(id);  //fetch data from pin table by id
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if (existsUser) {
            if (passwordEncryptor.checkPassword(pin, pinEntity1.getPin())) {
                System.out.println("User is validated");
                return true;

            } else {
                System.out.println("User Pin is incorrect");
            }
        } else {
            System.out.println("User is not exist");
        }
        return false;
    }

    @Override
    public UserEntity setDefaultAccount(int maId, int defaultAccount) {
        UserEntity user = userRepo.getUserByMaId(maId);
        user.setDefaultAccount(defaultAccount);
        return userRepo.save(user);
    }

    @Override
    public UserAccountEntity getDefaultAccount(int maId) {
        UserEntity user = userRepo.getUserByMaId(maId);
        return userAccountService.getUserAccountByAccountNumberAndMaId(user.getDefaultAccount(), user.getMaId());
    }

    @Override
    public Boolean isAdmin(int id) {
        try {
            UserEntity user = userRepo.getUserByUserId(id);
            return user.getRole() == Role.ADMIN;
        } catch (Exception exception) {
            throw new NullPointerException();
        }
    }
}
