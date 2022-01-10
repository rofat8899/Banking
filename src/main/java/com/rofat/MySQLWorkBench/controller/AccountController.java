package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;
import com.rofat.MySQLWorkBench.service.UserAccountApproveService;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserAccountApproveService userAccountApproveService;
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("")
    public List<UserAccountDTO> getAllAccounts() {
        return userAccountService.getAllUserAccount();
    }

    @GetMapping("/{id}")          //Get User By Master Account ID
    public List<UserAccountDTO> getUserAccountByMasterAccId(@PathVariable Integer id) {
        return userAccountService.getUserAccountByMasterAccId(id);
    }

    @PostMapping("/{id}")
    public UserAccountApproveEntity addUserAccount(@RequestBody UserAccountApproveEntity userAccountApprove, @PathVariable("id") Integer id) {
        userAccountApprove.setMaId(id);
        return userAccountApproveService.addOrUpdateUserAccountApprove_(userAccountApprove);
    }

    @PutMapping("/{id}")        //Delete User Account
    public String deleteUserAccount(@PathVariable("id") Integer accountNumber) {
        userAccountService.deleteUserAccount(accountNumber);
        return "Successfully deleted";
    }

    @GetMapping("/activate/{id}") //Activate User Account
    public UserAccountDTO activateUserAccount(@RequestBody Map<String, Object> obj, @PathVariable("id") int accountNumber) {
        String userId = (String) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        return userAccountApproveService.activateUserAccount(userId, userPin, accountNumber);
    }
}
