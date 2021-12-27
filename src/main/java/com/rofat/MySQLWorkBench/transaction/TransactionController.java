package com.rofat.MySQLWorkBench.transaction;

import com.rofat.MySQLWorkBench.transaction.model.TransactionHistory;
import com.rofat.MySQLWorkBench.transaction.service.TransactionService;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
import com.rofat.MySQLWorkBench.user.service.UserService;
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
    public UserAccount cashOut (@PathVariable("id") int accNum, @RequestParam double amount, @RequestBody Map<String, Object> obj){
        int userId = (int) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        Boolean validate = userService.validation(userId,userPin);      //validate user
        if(validate)
        {
            return transactionService.cashOut(accNum,amount,false);
        }
       return null;
    }

    @PutMapping("/cashin/{id}")     //Cash In
    public UserAccount cashIn (@PathVariable("id") int accNum, @RequestParam double amount, @RequestBody Map<String, Object> obj){
        int userId = (int) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        Boolean validate = userService.validation(userId,userPin);      //validate user
        if(validate)
        {
            return transactionService.cashIn(accNum,amount,false);
        }
        return null;
    }

    @PutMapping("/transfer/{id}")   //Transfer Money
    public TransactionHistory transferMoney (@PathVariable("id") int sender, @RequestParam double amount, @RequestParam(name = "recAcc") int recAcc, @RequestBody Map<String, Object> obj){
        int userId = (int) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        Boolean validate = userService.validation(userId,userPin);      //validate user
        if(validate)
        {
            return transactionService.transferMoney(sender,amount,recAcc);
        }
        return null;
    }
}
