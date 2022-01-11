package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.model.SecurityEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.repository.SecurityRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.SecurityService;
import com.rofat.MySQLWorkBench.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;

@Service
public class SecurityServiceImp implements SecurityService {
    @Autowired
    private SecurityRepo securityRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Override
    public SecurityEntity generateSecurityCode(Map<String,Object> obj) {
        String userId = (String) obj.get("userId");
        Boolean validate = userService.validation(obj);
        UserEntity user = userRepo.getUserByUserId(userId);
        SecurityEntity existedsecurity = securityRepo.getSecurityByUserId(userId);
        LocalDateTime datetime = LocalDateTime.now();

        Random rnd = new Random();
        int ranNum = rnd.nextInt(999999);
        String securityCode = String.valueOf(ranNum);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(securityCode);
        Boolean isExisted = securityRepo.existsByUserId(userId);
        SecurityEntity securityEntity = new SecurityEntity();
        if (validate) {
            if (isExisted) {

                securityEntity.setSecurityId(existedsecurity.getSecurityId());
                securityEntity.setSecurityCode(encryptedPassword);
                securityEntity.setUserId(existedsecurity.getUserId());
                securityEntity.setNumberGenerate(0);
                securityEntity.setModifiedOn(datetime);
                securityEntity.setModifiedBy(user.getName());
                securityEntity.setCreatedOn(existedsecurity.getCreatedOn());
                securityEntity.setCreatedBy(existedsecurity.getCreatedBy());
                System.out.format("You successfully regenerated the code !\nYour new security code is %d\n", ranNum);
                return securityRepo.save(securityEntity);
            } else {
                securityEntity.setSecurityCode(encryptedPassword);
                securityEntity.setUserId(user.getUserId());
                securityEntity.setModifiedOn(datetime);
                securityEntity.setModifiedBy(user.getName());
                securityEntity.setCreatedOn(datetime);
                securityEntity.setCreatedBy(user.getName());
                System.out.format("You successfully generated the code !\nYour security code is %d\n", ranNum);
                return securityRepo.save(securityEntity);
            }

        }
        return null;
    }

    @Override
    public Boolean validateSecureCode(Map<String,Object> obj) {
        String userId = (String) obj.get("userId");
        String code = (String) obj.get("userPin");
        Boolean existsUser = userRepo.existsByUserId(userId);
        SecurityEntity securityEntity = securityRepo.getSecurityByUserId(userId);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        LocalDateTime now = LocalDateTime.now();
        long diff = ChronoUnit.SECONDS.between(securityEntity.getModifiedOn(), now);

        if (!code.equals("")) {
            if (existsUser) {
                if (diff < 600) // 10 minutes
                {
                    if (securityEntity.getNumberGenerate() < 5) {
                        if (passwordEncryptor.checkPassword(code, securityEntity.getSecurityCode())) {
                            System.out.println("Code accepted");
                            return true;

                        } else {
                            int numGen = securityEntity.getNumberGenerate();
                            securityEntity.setNumberGenerate(numGen + 1);
                            securityRepo.save(securityEntity);
                            System.out.println("Code is incorrect");
                            System.out.format("You have %d attempted\n", securityEntity.getNumberGenerate());
                        }
                    } else {
                        System.out.format("You have reached (%d) limit attempt.\n", securityEntity.getNumberGenerate());
                    }
                } else {
                    System.out.println("Code expired.");
                }

            } else {
                System.out.println("User is not exist");
            }

        } else {
            System.out.println("Please check your code or user ID again.");
        }

        return false;
    }


}
