package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.model.SecurityEntity;
import com.rofat.MySQLWorkBench.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @PostMapping()
    public SecurityEntity generateSecurcode(@RequestBody Map<String, Object> obj) {
        return securityService.generateSecurityCode(obj);
    }

    @GetMapping()
    public Boolean validateSecureCode(@RequestBody Map<String, Object> obj) {
        return securityService.validateSecureCode(obj);
    }
}
