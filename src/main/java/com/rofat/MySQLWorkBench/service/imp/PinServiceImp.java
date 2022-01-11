package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.PinCodeDTO;
import com.rofat.MySQLWorkBench.exception.BadRequestException;
import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.repository.PinRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.PinService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PinServiceImp implements PinService {


    @Autowired
    private PinRepo pinRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public PinCodeDTO save(PinEntity pinEntity) {
        Boolean existsUser = userRepo.existsByUserId(pinEntity.getUserId());
        Boolean existsPin = pinRepo.existsByUserIdAndPinIsNotNull(pinEntity.getUserId());
        if (existsUser) {
            if (!existsPin) {
                StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                String encryptedPassword = passwordEncryptor.encryptPassword(pinEntity.getPin());
                pinEntity.setPin(encryptedPassword);
                System.out.println("Pin is adding now");
                return new PinCodeDTO(pinRepo.save(pinEntity)) ;
            }
            System.out.println("User and pin is existed ");
            throw new BadRequestException("Pin existed");
        }
        return null;
    }

    @Override
    public PinCodeDTO findByUserId(String id) {
        return new PinCodeDTO(pinRepo.findPinByUserId(id)) ;
    }

    @Override
    public List<PinCodeDTO> findAll() {
        List<PinEntity> pinEntities=pinRepo.findAll();
        List<PinCodeDTO> pinCodeDTO = new ArrayList<>();
        for(PinEntity each:pinEntities)
        {
            pinCodeDTO.add(new PinCodeDTO(each));
        }
        return pinCodeDTO;
    }
}
