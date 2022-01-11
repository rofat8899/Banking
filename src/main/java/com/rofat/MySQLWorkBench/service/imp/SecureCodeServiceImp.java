package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.ResponseMessageDTO;
import com.rofat.MySQLWorkBench.dto.SecureCodeDTO;
import com.rofat.MySQLWorkBench.model.SecureCodeEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.repository.SecurityRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.SecureCodeService;
import com.rofat.MySQLWorkBench.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;

@Service
public class SecureCodeServiceImp implements SecureCodeService {
    @Autowired
    private SecurityRepo securityRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Override
    public SecureCodeDTO generateSecurityCode(Map<String, Object> obj) {
        String userId = (String) obj.get("userId");
        Boolean validate = userService.validation(obj);
        UserEntity user = userRepo.getUserByUserId(userId);
        SecureCodeEntity existed_security = securityRepo.getSecurityByUserId(userId);
        LocalDateTime datetime = LocalDateTime.now();

        Random rnd = new Random();
        int ranNum = rnd.nextInt(999999);
        String securityCode = String.valueOf(ranNum);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(securityCode);
        Boolean isExisted = securityRepo.existsByUserId(userId);
        SecureCodeEntity secureCodeEntity = new SecureCodeEntity();
        if (validate) {
            if (isExisted) {

                secureCodeEntity.setSecurityId(existed_security.getSecurityId());
                secureCodeEntity.setSecurityCode(encryptedPassword);
                secureCodeEntity.setUserId(existed_security.getUserId());
                secureCodeEntity.setNumberGenerate(0);
                secureCodeEntity.setModifiedOn(datetime);
                secureCodeEntity.setModifiedBy(user.getName());
                secureCodeEntity.setCreatedOn(existed_security.getCreatedOn());
                secureCodeEntity.setCreatedBy(existed_security.getCreatedBy());
                System.out.format("You successfully regenerated the code !\nYour new security code is %d\n", ranNum);
                return new SecureCodeDTO(securityCode, securityRepo.save(secureCodeEntity));
            } else {
                secureCodeEntity.setSecurityCode(encryptedPassword);
                secureCodeEntity.setUserId(user.getUserId());
                secureCodeEntity.setModifiedOn(datetime);
                secureCodeEntity.setModifiedBy(user.getName());
                secureCodeEntity.setCreatedOn(datetime);
                secureCodeEntity.setCreatedBy(user.getName());
                System.out.format("You successfully generated the code !\nYour security code is %d\n", ranNum);
                return new SecureCodeDTO(securityCode, securityRepo.save(secureCodeEntity));
            }
        }
        return null;
    }

    @Override
    public ResponseMessageDTO validateSecureCode(Map<String, Object> obj) {
        if (validate(obj)) {
            return new ResponseMessageDTO("success");
        } else {
            return new ResponseMessageDTO("failed to validate the code");
        }
    }

    private Boolean validate(Map<String, Object> obj) {
        String userId = (String) obj.get("userId");
        String code = (String) obj.get("code");
        Boolean existsUser = userRepo.existsByUserId(userId);
        SecureCodeEntity secureCodeEntity = securityRepo.getSecurityByUserId(userId);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        LocalDateTime now = LocalDateTime.now();
        long diff = ChronoUnit.SECONDS.between(secureCodeEntity.getModifiedOn(), now);

        if (!code.equals("")) {
            if (existsUser) {
                if (diff < 600) // 10 minutes
                {
                    if (secureCodeEntity.getNumberGenerate() < 5) {
                        if (passwordEncryptor.checkPassword(code, secureCodeEntity.getSecurityCode())) {
                            System.out.println("Code accepted");
                            return true;

                        } else {
                            int numGen = secureCodeEntity.getNumberGenerate();
                            secureCodeEntity.setNumberGenerate(numGen + 1);
                            securityRepo.save(secureCodeEntity);
                            System.out.println("Code is incorrect");
                            System.out.format("You have %d attempted\n", secureCodeEntity.getNumberGenerate());
                        }
                    } else {
                        System.out.format("You have reached (%d) limit attempt.\n", secureCodeEntity.getNumberGenerate());
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
