package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.dto.TransferDTO;
import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.model.TransactionEntity;

public interface TransactionService {

    boolean cashOut(int accNum, double amount, boolean isTransfer);
    boolean cashIn(int accNum, double amount, boolean isTransfer);
    UserAccountDTO cashOutUsr(int accNum, double amount, boolean isTransfer);
    UserAccountDTO cashInUsr(int accNum, double amount, boolean isTransfer);
    TransferDTO transferMoney(int senderAcc, double amount, int recAcc);
    TransactionEntity addTransaction(TransactionEntity transactionEntity);
    boolean transferMoney(int senderAcc, CurrencyType sendCurrency, double amountUSD, double amountKHR, int recAcc, CurrencyType receiverCurrency);
}
