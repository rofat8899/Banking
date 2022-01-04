package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.constant.TransactionType;
import com.rofat.MySQLWorkBench.model.TransactionHistoryEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.repository.TransactionHistoryRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    private UserAccRepo userAccRepo;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    LocalDateTime now = LocalDateTime.now();

    @Override   //Cash Out
    public UserAccountEntity cashOut(int accNum, double amount, boolean isTransfer) {
        return cashOperation(accNum, amount, isTransfer, true, "Unable to cash-out", TransactionType.CASH_OUT);
    }

    @Override   //Cash In
    public UserAccountEntity cashIn(int accNum, double amount, boolean isTransfer) {
        return cashOperation(accNum, amount, isTransfer, false, "Unable to cash-in", TransactionType.CASH_IN);
    }

    @Override   //Transfer Money
    public TransactionHistoryEntity transferMoney(int senderAcc, double amount, int recAcc) {
        cashIn(recAcc, amount, true);
        cashOut(senderAcc, amount, true);
        TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity(senderAcc, now, TransactionType.TRANSFER, senderAcc, recAcc, amount);
        addTransaction(transactionHistoryEntity);
        return transactionHistoryEntity;
    }

    @Override   //Add transaction
    public TransactionHistoryEntity addTransaction(TransactionHistoryEntity transactionHistoryEntity) {
        return transactionHistoryRepo.save(transactionHistoryEntity);
    }

    //Check Amount
    private void checkAmount(double amount) throws Exception {
        if (amount < 0) {
            throw new Exception("Incorrect amount");
        }
    }

    private UserAccountEntity cashOperation(int accNum, double amount, boolean isTransfer, boolean isCashOut, String exception_string, TransactionType transactionType) {
        UserAccountEntity userAccountEntity = userAccRepo.getUserAccountByAccountNumber(accNum); //fetch user account
        double remaining = 0;
        try {
            checkAmount(amount); //Check Amount
            if (isCashOut) {
                remaining = userAccountEntity.getBalance() - amount;   //Deduct money from balance
            } else {
                remaining = userAccountEntity.getBalance() + amount; //Add money to balance
            }

            if (remaining < 0) {
                throw new Exception(exception_string);
            } else {
                userAccountEntity.setBalance(remaining);
                userAccRepo.save(userAccountEntity);
                if (!isTransfer) {
                    TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity(accNum, now, transactionType, amount);
                    addTransaction(transactionHistoryEntity);
                }
                return userAccountEntity;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }
}
