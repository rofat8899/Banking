package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.TransferDTO;
import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.service.TransactionService;
import com.rofat.MySQLWorkBench.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PutMapping("/cashout/{id}")    //Cash Out
    public UserAccountDTO cashOut(@PathVariable("id") int accNum, @RequestParam double amount, @RequestBody Map<String, Object> obj) {
        String userId = (String) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        Boolean validate = userService.validation(userId, userPin);      //validate user
        if (validate) {
            return transactionService.cashOutUsr(accNum, amount, false);
        }
        return null;
    }

    @PutMapping("/cashin/{id}")     //Cash In
    public UserAccountDTO cashIn(@PathVariable("id") int accNum, @RequestParam double amount, @RequestBody Map<String, Object> obj) {
        String userId = (String) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        Boolean validate = userService.validation(userId, userPin);      //validate user
        if (validate) {
            return transactionService.cashInUsr(accNum, amount, false);
        }
        return null;
    }

    @PutMapping("/transfer/{id}")   //Transfer Money
    public TransferDTO transferMoney(@PathVariable("id") int sender, @RequestParam double amount, @RequestParam(name = "recAcc") int recAcc, @RequestBody Map<String, Object> obj) throws Exception {
        String userId = (String) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        Boolean validate = userService.validation(userId, userPin);      //validate user
        if (validate) {
            return transactionService.transferMoney(sender, amount, recAcc);
        }
        return null;
    }
}
