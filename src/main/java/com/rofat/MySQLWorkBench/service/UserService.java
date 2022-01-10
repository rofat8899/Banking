package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUser();
    UserDTO getUserByMasterAccId(Integer id);
    UserDTO getUserByUserId(String id);


    UserDTO addUser(UserEntity user);
    UserDTO setDefaultAccount(int maId, int defaultAccount);

    Boolean validation(String id, String pin);
    Boolean isAdmin(String id);
}
