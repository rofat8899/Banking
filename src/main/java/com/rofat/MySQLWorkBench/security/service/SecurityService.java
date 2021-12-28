package com.rofat.MySQLWorkBench.security.service;

import com.rofat.MySQLWorkBench.security.model.Security;

public interface SecurityService {
    Security generateSecurityCode(int userId, String userPin);
}
