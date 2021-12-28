package com.rofat.MySQLWorkBench.security;

import com.rofat.MySQLWorkBench.security.model.Security;
import com.rofat.MySQLWorkBench.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
