package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.AccountBalanceDTO;
import com.rofat.MySQLWorkBench.dto.TransferDTO;
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
    public AccountBalanceDTO cashOut(@PathVariable("id") int accNum, @RequestParam double amount, @RequestBody Map<String, Object> obj) {
        Boolean validate = userService.validation(obj);      //validate user
        if (validate) {
            return transactionService.cashOutUsr(accNum, amount, false);
        }
        return null;
    }

    @PutMapping("/cashin/{id}")     //Cash In
    public AccountBalanceDTO cashIn(@PathVariable("id") int accNum, @RequestParam double amount, @RequestBody Map<String, Object> obj) {
        Boolean validate = userService.validation(obj);      //validate user
        if (validate) {
            return transactionService.cashInUsr(accNum, amount, false);
        }
        return null;
    }

    @PutMapping("/transfer/{id}")   //Transfer Money
    public TransferDTO transferMoney(@PathVariable("id") int sender, @RequestParam double amount, @RequestParam(name = "sendTo") int sendTo, @RequestBody Map<String, Object> obj) throws Exception {
        Boolean validate = userService.validation(obj);      //validate user
        if (validate) {
            return transactionService.transferMoney(sender, amount, sendTo);
        }
        return null;
    }
}
