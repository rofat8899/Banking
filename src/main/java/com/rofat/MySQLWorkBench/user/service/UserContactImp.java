package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.Repo.UserContactRepo;

import com.rofat.MySQLWorkBench.user.model.UserContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserContactImp implements UserContactService {

    @Autowired
    private UserContactRepo userContactRepo;

    @Override
    public UserContact getUserAddressById(Integer id) {
        return userContactRepo.findUserContactByMaId(id);
    }

    @Override
    public UserContact addUserContact(UserContact userContact) {
        return userContactRepo.save(userContact);
    }
}
