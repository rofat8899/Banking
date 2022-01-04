package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.SecurityEntity;

public interface SecurityService {
    SecurityEntity generateSecurityCode(int userId, String userPin);

    Boolean validateSecureCode(String code, int userId);
}