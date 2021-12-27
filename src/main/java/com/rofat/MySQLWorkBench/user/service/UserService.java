package com.rofat.MySQLWorkBench.user.service;

import com.rofat.MySQLWorkBench.user.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User addUser(User user);

    User getUserByMasterAccId(Integer id);

    User getUserByUserId(Integer id);

    Boolean validation(Integer id, String pin);
}
