package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.UserContactEntity;

public interface UserContactService {

    UserContactEntity getUserAddressById(Integer id);

    UserContactEntity addUserContact(UserContactEntity userContactEntity);
}
