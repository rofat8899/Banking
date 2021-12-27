package com.rofat.MySQLWorkBench.pin.service;

import com.rofat.MySQLWorkBench.exception.BadRequestException;
import com.rofat.MySQLWorkBench.pin.model.Pin;
import com.rofat.MySQLWorkBench.pin.repo.PinRepo;
import com.rofat.MySQLWorkBench.user.Repo.UserRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinServiceImp implements PinService{


    @Autowired
    private PinRepo pinRepo;
    @Autowired
    private UserRepo userRepo;


//    AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
//    textEncryptor.setPassword(myEncryptionPassword);
//    String myEncryptedText = textEncryptor.encrypt(myText);
//    String plainText = textEncryptor.decrypt(myEncryptedText);

    @Override
    public Pin save(Pin pin) {
        Boolean existsUser = userRepo.existsByUserId(pin.getUserId());
        Boolean existsPin  = pinRepo.existsByUserIdAndPinIsNotNull(pin.getUserId());

        //System.out.println(existsPin);
         if(existsUser){
             if(!existsPin){
                 StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                 String encryptedPassword = passwordEncryptor.encryptPassword(pin.getPin());
                 pin.setPin(encryptedPassword);
                 //System.out.println(encryptedPassword);
                 System.out.println("Pin is adding now");
                 return pinRepo.save(pin);
             }
            System.out.println("User and pin is existed ");
            throw new BadRequestException("Pin existed");
        }
        return  null;
    }

    @Override
    public Pin findByUserId(int id) {
        return pinRepo.findPinByUserId(id);
    }

    @Override
    public List<Pin> findAll() {
        return pinRepo.findAll();
    }
}
