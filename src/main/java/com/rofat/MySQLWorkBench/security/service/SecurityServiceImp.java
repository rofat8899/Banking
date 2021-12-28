package com.rofat.MySQLWorkBench.security.service;

import com.rofat.MySQLWorkBench.security.model.Security;
import com.rofat.MySQLWorkBench.security.repo.SecurityRepo;
import com.rofat.MySQLWorkBench.user.Repo.UserRepo;
import com.rofat.MySQLWorkBench.user.model.User;
import com.rofat.MySQLWorkBench.user.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class SecurityServiceImp implements SecurityService{
    @Autowired
    private SecurityRepo securityRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Override
    public Security generateSecurityCode(int userId, String userPin) {

        Boolean validate = userService.validation(userId,userPin);
        User user = userService.getUserByUserId(userId);
        Security existedsecurity = securityRepo.getSecurityByUserId(userId);
        Date date = new Date();
        Random rnd = new Random();
        int ranNum = rnd.nextInt(999999);
        String securityCode = String.valueOf(ranNum);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(securityCode);
        Boolean isExisted = securityRepo.existsByUserId(userId);
        Security security = new Security();
        if(validate)
        {
            if(isExisted)
            {

                security.setSecurityId(existedsecurity.getSecurityId());
                security.setSecurityCode(encryptedPassword);
                security.setUserId(existedsecurity.getUserId());
                security.setNumberGenerate(0);
                security.setModifiedOn(date);
                security.setModifiedBy(user.getName());
                security.setCreatedOn(existedsecurity.getCreatedOn());
                security.setCreatedBy(existedsecurity.getCreatedBy());
                System.out.format("You successfully regenerated the code !\nYour new security code is %d\n",ranNum);
                return  securityRepo.save(security);
            }
            else
            {
                security.setSecurityCode(encryptedPassword);
                security.setUserId(user.getUserId());
                security.setModifiedOn(date);
                security.setModifiedBy(user.getName());
                security.setCreatedOn(date);
                security.setCreatedBy(user.getName());
                System.out.format("You successfully generated the code !\nYour security code is %d\n",ranNum);
                return securityRepo.save(security);
            }

        }
        return null;
    }

    @Override
    public Boolean validateSecureCode(String code, int userId) {
        Boolean existsUser = userRepo.existsByUserId(userId);
        Security security = securityRepo.getSecurityByUserId(userId);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(existsUser)
        {
            if(security.getNumberGenerate()<5)
            {
                if (passwordEncryptor.checkPassword(code, security.getSecurityCode())) {
                    System.out.println("code accepted");
                    return true;

                } else {
                    int numGen = security.getNumberGenerate();
                    security.setNumberGenerate(numGen+1);
                    securityRepo.save(security);
                    System.out.println("Code is incorrect");
                    System.out.format("You have %d attempted\n",security.getNumberGenerate());
                }
            }
            else
            {
                System.out.format("You have reached (%d) limit attempt.\n",security.getNumberGenerate());
            }

        }
        else {
            System.out.println("User is not exist");
        }
        return false;
    }


}
