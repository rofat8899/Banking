package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUser();
    UserDTO getUserByMasterAccId(Integer id);
    UserEntity getUserByUserId(String id);
    UserAccountEntity getDefaultAccount(int maId);

    UserEntity addUser(UserEntity user);

    UserEntity setDefaultAccount(int maId, int defaultAccount);

    Boolean validation(String id, String pin);
    Boolean isAdmin(String id);
}
