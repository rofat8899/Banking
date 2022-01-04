package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUser();
    UserEntity getUserByMasterAccId(Integer id);
    UserEntity getUserByUserId(Integer id);
    UserAccountEntity getDefaultAccount(int maId);

    UserEntity addUser(UserEntity user);

    UserEntity setDefaultAccount(int maId, int defaultAccount);

    Boolean validation(Integer id, String pin);
    Boolean isAdmin(int id);
}
