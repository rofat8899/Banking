package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.ResponseMessageDTO;
import com.rofat.MySQLWorkBench.dto.SecureCodeDTO;

import java.util.Map;

public interface SecureCodeService {
    SecureCodeDTO generateSecurityCode(Map<String, Object> obj);

    ResponseMessageDTO validateSecureCode(Map<String, Object> obj);
}
