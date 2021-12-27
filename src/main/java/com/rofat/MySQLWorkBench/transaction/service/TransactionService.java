package com.rofat.MySQLWorkBench.transaction.service;

import com.rofat.MySQLWorkBench.transaction.model.TransactionHistory;
import com.rofat.MySQLWorkBench.user.model.UserAccount;

public interface TransactionService {

    UserAccount cashOut(int accNum, double amount, boolean isTransfer);
    UserAccount cashIn(int accNum, double amount, boolean isTransfer);
    TransactionHistory transferMoney(int senderAcc, double amount, int recAcc);
    TransactionHistory addTransaction(TransactionHistory transactionHistory);
}
