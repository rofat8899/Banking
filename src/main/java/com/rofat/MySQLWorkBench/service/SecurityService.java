package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.SecurityEntity;

import java.util.Map;

public interface SecurityService {
    SecurityEntity generateSecurityCode(Map<String, Object> obj);

    Boolean validateSecureCode(Map<String, Object> obj);
}
