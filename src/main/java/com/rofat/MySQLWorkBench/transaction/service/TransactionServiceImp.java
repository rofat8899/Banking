package com.rofat.MySQLWorkBench.transaction.service;

import com.rofat.MySQLWorkBench.transaction.Repo.TransactionHistoryRepo;
import com.rofat.MySQLWorkBench.transaction.constant.TransactionType;
import com.rofat.MySQLWorkBench.transaction.model.TransactionHistory;
import com.rofat.MySQLWorkBench.user.Repo.UserAccRepo;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImp implements TransactionService{
    @Autowired
    private UserAccRepo userAccRepo;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    LocalDateTime now = LocalDateTime.now();

    @Override   //Cash Out
    public UserAccount cashOut(int accNum, double amount, boolean isTransfer) {
            return  cashOperation(accNum,amount,isTransfer,true,"Unable to cash-out",TransactionType.CASH_OUT);
    }

    @Override   //Cash In
    public UserAccount cashIn(int accNum, double amount, boolean isTransfer) {
        return  cashOperation(accNum,amount,isTransfer,false,"Unable to cash-in",TransactionType.CASH_IN);
    }

    @Override   //Transfer Money
    public TransactionHistory transferMoney(int senderAcc, double amount, int recAcc) {
        cashIn(recAcc,amount,true);
        cashOut(senderAcc,amount,true);
        TransactionHistory transactionHistory = new TransactionHistory(senderAcc,now, TransactionType.TRANSFER,senderAcc,recAcc,amount);
        addTransaction(transactionHistory);
        return transactionHistory;
    }

    @Override   //Add transaction
    public TransactionHistory addTransaction(TransactionHistory transactionHistory) {
        return transactionHistoryRepo.save(transactionHistory);
    }

    //Check Amount
    private void checkAmount(double amount) throws Exception {
        if(amount<0)
        {
            throw new Exception("Incorrect amount");
        }
    }

    private UserAccount cashOperation(int accNum, double amount, boolean isTransfer,boolean isCashOut,String exception_string,TransactionType transactionType){
        UserAccount userAccount= userAccRepo.getUserAccountByAccountNumber(accNum); //fetch user account
        double remaining=0;
        try{
            checkAmount(amount); //Check Amount
            if(isCashOut)
            {
                remaining=userAccount.getBalance() - amount;   //Deduct money from balance
            }
            else{
                remaining=userAccount.getBalance() + amount; //Add money to balance
            }

            if(remaining<0)
            {
                throw new Exception(exception_string);
            }
            else{
                userAccount.setBalance(remaining);
                userAccRepo.save(userAccount);
                if(!isTransfer)
                {
                    TransactionHistory transactionHistory = new TransactionHistory(accNum,now, transactionType,amount);
                    addTransaction(transactionHistory);
                }
                return userAccount;
            }
        } catch (Exception exception)
        {
            System.out.println(exception);
        }
        return null;
    }
}
