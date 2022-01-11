package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.ResponseMessageDTO;
import com.rofat.MySQLWorkBench.dto.SecureCodeDTO;
import com.rofat.MySQLWorkBench.service.SecureCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/secure-code")
public class SecureCodeController {
    @Autowired
    private SecureCodeService secureCodeService;

    @PostMapping("/generate")
    public SecureCodeDTO generateSecurcode(@RequestBody Map<String, Object> obj) {
        return secureCodeService.generateSecurityCode(obj);
    }

    @GetMapping("/validate")
    public ResponseMessageDTO validateSecureCode(@RequestBody Map<String, Object> obj) {
        return secureCodeService.validateSecureCode(obj);
    }

}
