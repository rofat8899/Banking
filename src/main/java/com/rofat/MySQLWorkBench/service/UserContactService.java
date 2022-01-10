package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserContactDTO;
import com.rofat.MySQLWorkBench.model.UserContactEntity;

public interface UserContactService {

    UserContactDTO getUserAddressById(Integer id);

    UserContactDTO addUserContact(UserContactEntity userContactEntity);
}
