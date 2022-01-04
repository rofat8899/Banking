package com.rofat.MySQLWorkBench.service.imp;

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
    public UserContactEntity getUserAddressById(Integer id) {
        return userContactRepo.findUserContactByMaId(id);
    }

    @Override
    public UserContactEntity addUserContact(UserContactEntity userContactEntity) {
        return userContactRepo.save(userContactEntity);
    }
}
