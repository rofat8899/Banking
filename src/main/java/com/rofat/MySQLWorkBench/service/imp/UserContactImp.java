package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.UserContactDTO;
import com.rofat.MySQLWorkBench.model.UserContactEntity;
import com.rofat.MySQLWorkBench.repository.UserContactRepo;
import com.rofat.MySQLWorkBench.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserContactImp implements UserContactService {

    @Autowired
    private UserContactRepo userContactRepo;

    @Override
    public UserContactDTO getUserAddressById(Integer id) {
        return new UserContactDTO(userContactRepo.findUserContactByMaId(id));
    }

    @Override
    public UserContactDTO addUserContact(UserContactEntity userContactEntity) {
        return new UserContactDTO(userContactRepo.save(userContactEntity));
    }
}
