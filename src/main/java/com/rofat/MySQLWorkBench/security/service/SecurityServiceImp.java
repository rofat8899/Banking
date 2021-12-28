package com.rofat.MySQLWorkBench.security.service;

import com.rofat.MySQLWorkBench.security.model.Security;
import com.rofat.MySQLWorkBench.security.repo.SecurityRepo;
import com.rofat.MySQLWorkBench.user.model.User;
import com.rofat.MySQLWorkBench.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class SecurityServiceImp implements SecurityService{
    @Autowired
    private SecurityRepo securityRepo;

    @Autowired
    private UserService userService;

    @Override
    public Security generateSecurityCode(int userId, String userPin) {
        Boolean validate = userService.validation(userId,userPin);
        User user = userService.getUserByUserId(userId);
        Date date = new Date();
        Random rnd = new Random();
        int securityCode = rnd.nextInt(999999);
        Boolean isExisted = securityRepo.existsByUserId(userId);
        Security security = new Security();
        if(validate)
        {
            if(isExisted)
            {
                security.setSecurityCode(securityCode);
                security.setNumberGenerate(0);
                security.setModifiedOn(date);
                security.setModifiedBy(user.getName());
                return  securityRepo.save(security);
            }
            else
            {
                security.setSecurityCode(securityCode);
                security.setUserId(user.getUserId());
                security.setModifiedOn(date);
                security.setModifiedBy(user.getName());
                security.setCreatedOn(date);
                security.setCreatedBy(user.getName());
                return securityRepo.save(security);
            }

        }
        return null;
    }
}
