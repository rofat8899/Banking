package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.exception.BadRequestException;
import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.repository.PinRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.PinService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinServiceImp implements PinService {


    @Autowired
    private PinRepo pinRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public PinEntity save(PinEntity pinEntity) {
        Boolean existsUser = userRepo.existsByUserId(pinEntity.getUserId());
        Boolean existsPin = pinRepo.existsByUserIdAndPinIsNotNull(pinEntity.getUserId());
        if (existsUser) {
            if (!existsPin) {
                StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                String encryptedPassword = passwordEncryptor.encryptPassword(pinEntity.getPin());
                pinEntity.setPin(encryptedPassword);
                System.out.println("Pin is adding now");
                return pinRepo.save(pinEntity);
            }
            System.out.println("User and pin is existed ");
            throw new BadRequestException("Pin existed");
        }
        return null;
    }

    @Override
    public PinEntity findByUserId(int id) {
        return pinRepo.findPinByUserId(id);
    }

    @Override
    public List<PinEntity> findAll() {
        return pinRepo.findAll();
    }
}
