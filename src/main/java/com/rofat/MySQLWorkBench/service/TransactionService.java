package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.TransactionHistoryEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;

public interface TransactionService {

    UserAccountEntity cashOut(int accNum, double amount, boolean isTransfer);
    UserAccountEntity cashIn(int accNum, double amount, boolean isTransfer);
    TransactionHistoryEntity transferMoney(int senderAcc, double amount, int recAcc);
    TransactionHistoryEntity addTransaction(TransactionHistoryEntity transactionHistoryEntity);
}
