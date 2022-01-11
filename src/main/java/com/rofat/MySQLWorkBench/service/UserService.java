package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserDTO> getAllUser();
    UserDTO getUserByMasterAccId(Integer id);
    UserDTO getUserByUserId(String id);


    UserDTO addUser(UserEntity user);
    UserDTO setDefaultAccount(int maId, int defaultAccount);

    Boolean validation(Map<String,Object> obj);
    Boolean isAdmin(String id);
}
