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

    @GetMapping("/{id}")
    public List<UserAccountDTO> getUserAccountByMasterAccId(@PathVariable Integer id) {
        return userAccountService.getUserAccountByMasterAccId(id);
    }

    @PostMapping("/{id}")
    public UserAccountApproveEntity addUserAccount(@RequestBody UserAccountApproveEntity userAccountApprove, @PathVariable("id") Integer id) {
        userAccountApprove.setMaId(id);
        return userAccountApproveService.addOrUpdateUserAccountApprove_(userAccountApprove);
    }

    @PutMapping("/{id}")
    public String deleteUserAccount(@PathVariable("id") Integer accountNumber) {
        userAccountService.deleteUserAccount(accountNumber);
        return "Successfully deleted";
    }

    @GetMapping("/activate/{id}")
    public UserAccountDTO activateUserAccount(@RequestBody Map<String, Object> obj, @PathVariable("id") int accountNumber) {
        return userAccountApproveService.activateUserAccount(obj, accountNumber);
    }

    @GetMapping("/default/{id}")
    public UserAccountDTO getDefaultAccount(@PathVariable("id") int maId) {
        return userAccountService.getDefaultAccountByMasterAccId(maId);
    }
}
