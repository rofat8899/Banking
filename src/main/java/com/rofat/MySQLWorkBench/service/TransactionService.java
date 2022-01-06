package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.model.TransactionHistoryEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;

public interface TransactionService {

    boolean cashOut(int accNum, double amount, boolean isTransfer);
    boolean cashIn(int accNum, double amount, boolean isTransfer);
    UserAccountEntity cashOutUsr(int accNum, double amount, boolean isTransfer);
    UserAccountEntity cashInUsr(int accNum, double amount, boolean isTransfer);
    TransactionHistoryEntity transferMoney(int senderAcc, double amount, int recAcc);
    TransactionHistoryEntity addTransaction(TransactionHistoryEntity transactionHistoryEntity);
    TransactionHistoryEntity transferMoney(int senderAcc, CurrencyType sendCurrency, double amount, int recAcc, CurrencyType receiverCurrency);
}
