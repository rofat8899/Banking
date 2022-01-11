package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.constant.Role;
import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.exception.BadRequestException;
import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.repository.PinRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.UserService;
import lombok.AllArgsConstructor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final PinRepo pinRepo;

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> userDTO = new ArrayList<>();
        for (UserEntity each : userRepo.findAll()) {
            userDTO.add(new UserDTO(each));
        }
        return userDTO;
    }

    @Override
    public UserDTO addUser(UserEntity user) {
        Boolean existsUser = userRepo.existsByUserId(user.getUserId());
        if (existsUser) {
            throw new BadRequestException("User existed");
        }
        return new UserDTO(userRepo.save(user));
    }

    @Override
    public UserDTO getUserByMasterAccId(Integer id) {
        UserEntity uEntity = userRepo.getUserByMaId(id);
        return new UserDTO(uEntity);
    }

    @Override
    public UserDTO getUserByUserId(String id) {
        return new UserDTO(userRepo.getUserByUserId(id));
    }

    @Override
    public Boolean validation(Map<String, Object> obj) {
        String id = (String) obj.get("userId");
        String pin = (String) obj.get("userPin");
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
    public UserDTO setDefaultAccount(int maId, int defaultAccount) {
        UserEntity user = userRepo.getUserByMaId(maId);
        user.setDefaultAccount(defaultAccount);
        return new UserDTO(userRepo.save(user));
    }

    @Override
    public Boolean isAdmin(String id) {
        try {
            UserEntity user = userRepo.getUserByUserId(id);
            return user.getRole() == Role.ADMIN;
        } catch (Exception exception) {
            throw new NullPointerException();
        }
    }
}
