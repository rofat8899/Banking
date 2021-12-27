package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.model.UserContact;

public interface UserContactService {
    UserContact getUserAddressById(Integer id);

    UserContact addUserContact(UserContact userContact);
}
