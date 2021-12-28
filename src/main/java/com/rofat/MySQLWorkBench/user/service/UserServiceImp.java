package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.exception.BadRequestException;
import com.rofat.MySQLWorkBench.pin.model.Pin;
import com.rofat.MySQLWorkBench.pin.repo.PinRepo;
import com.rofat.MySQLWorkBench.user.Repo.UserRepo;
import com.rofat.MySQLWorkBench.user.model.User;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
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
    private final UserAccountService userAccountServiceice;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User user) {
        Boolean existsUser = userRepo.existsByUserId(user.getUserId());
        if(existsUser){
            throw new BadRequestException("User existed");
        }
        return userRepo.save(user);
    }

    @Override
    public User getUserByMasterAccId(Integer id) {
        return userRepo.getUserByMaId(id);
    }

    @Override
    public User getUserByUserId(Integer id) {
        return userRepo.getUserByUserId(id);
    }

    @Override
    public Boolean validation(Integer id, String pin) {
        Boolean existsUser = userRepo.existsByUserId(id); //check if database containing user or not
        Pin pin1= pinRepo.findPinByUserId(id);  //fetch data from pin table by id
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(existsUser)
        {
            if (passwordEncryptor.checkPassword(pin, pin1.getPin())) {
                System.out.println("User is validated");
                return true;

            } else {
                System.out.println("User Pin is incorrect");
            }
        }
        else {
            System.out.println("User is not exist");
        }
        return false;
    }

    @Override
    public User setDefaultAccount(int maId, int defaultAccount) {
        User user = userRepo.getUserByMaId(maId);
        user.setDefaultAccount(defaultAccount);
        return userRepo.save(user);
    }

    @Override
    public UserAccount getDefaultAccount(int maId) {
        User user = userRepo.getUserByMaId(maId);
        return userAccountServiceice.getUserAccountByAccountNumberAndMaId(user.getDefaultAccount(),user.getMaId());
    }
}
