package com.rofat.MySQLWorkBench.security;

import com.rofat.MySQLWorkBench.security.model.Security;
import com.rofat.MySQLWorkBench.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @PostMapping()
    public Security generateSecurcode(@RequestBody Map<String, Object> obj)
    {
        int userId = (int) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        return securityService.generateSecurityCode(userId,userPin);
    }

    @GetMapping()
    public Boolean validateSecureCode(@RequestBody Map<String, Object> obj )
    {
        String code = (String) obj.get("code");
        int userId = (int) obj.get("userId");
        return securityService.validateSecureCode(code,userId);
    }
}
