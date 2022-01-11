package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.constant.TransactionType;
import com.rofat.MySQLWorkBench.dto.AccountBalanceDTO;
import com.rofat.MySQLWorkBench.dto.TransferDTO;
import com.rofat.MySQLWorkBench.model.TransactionEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.repository.TransactionHistoryRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.repository.UserRepo;
import com.rofat.MySQLWorkBench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAccRepo userAccRepo;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    LocalDateTime now = LocalDateTime.now();

    @Override
    public boolean cashOut(int accNum, double amount, boolean isTransfer) {
        return cashOperation(accNum, amount, isTransfer, true, "Unable to cash-out", TransactionType.CASH_OUT);
    }

    @Override
    public boolean cashIn(int accNum, double amount, boolean isTransfer) {
        return cashOperation(accNum, amount, isTransfer, false, "Unable to cash-in", TransactionType.CASH_IN);
    }

    @Override
    public AccountBalanceDTO cashOutUsr(int accNum, double amount, boolean isTransfer) {
        if (cashOut(accNum, amount, isTransfer)) {
            return new AccountBalanceDTO(userAccRepo.getUserAccountByAccountNumber(accNum));
        }
        return null;
    }

    @Override
    public AccountBalanceDTO cashInUsr(int accNum, double amount, boolean isTransfer) {
        if (cashIn(accNum, amount, isTransfer)) {
            return new AccountBalanceDTO(userAccRepo.getUserAccountByAccountNumber(accNum));
        }
        return null;
    }

    @Override
    public TransferDTO transferMoney(int senderAcc, double amount, int recAcc) {
        double rate = 4000.0;
        double amountUSD = 0.0;
        double amountKHR = 0.0;

        UserAccountEntity senderAccount = userAccRepo.getUserAccountByAccountNumber(senderAcc);
        UserAccountEntity receiverAccount = userAccRepo.getUserAccountByAccountNumber(recAcc);
        UserEntity sender = userRepo.getUserByMaId(senderAccount.getMaId());
        UserEntity receiver = userRepo.getUserByMaId(receiverAccount.getMaId());

        if (senderAccount.getCurrencyType() == CurrencyType.USD) {
            amountUSD = amount;
            amountKHR = amount * rate;
        } else if (senderAccount.getCurrencyType() == CurrencyType.KHR) {
            amountUSD = amount / rate;
            amountKHR = amount;
        }
        boolean isSucceed = transferMoney(senderAcc, senderAccount.getCurrencyType(), amountUSD, amountKHR, recAcc, receiverAccount.getCurrencyType());
        return new TransferDTO(amountUSD, amountKHR, sender, receiver, now, isSucceed);
    }

    @Override
    public boolean transferMoney(int senderAcc, CurrencyType senderCurrency, double amountUSD, double amountKHR, int recAcc, CurrencyType receiverCurrency) {
        TransactionEntity transactionEntity;
        if (senderCurrency == receiverCurrency && receiverCurrency == CurrencyType.USD) {
            if (cashOut(senderAcc, amountUSD, true)) {
                cashIn(recAcc, amountUSD, true);
            } else {
                return false;
            }
        } else if (senderCurrency == receiverCurrency && receiverCurrency == CurrencyType.KHR) {
            if (cashOut(senderAcc, amountKHR, true)) {
                cashIn(recAcc, amountKHR, true);
            } else {
                return false;
            }
        } else if (senderCurrency == CurrencyType.KHR && receiverCurrency == CurrencyType.USD) {
            if (cashOut(senderAcc, amountKHR, true)) {
                cashIn(recAcc, amountUSD, true);
            } else {
                return false;
            }
        } else if (senderCurrency == CurrencyType.USD && receiverCurrency == CurrencyType.KHR) {
            if (cashOut(senderAcc, amountUSD, true)) {
                cashIn(recAcc, amountKHR, true);
            } else {
                return false;
            }
        } else {
            return false;
        }

        transactionEntity = new TransactionEntity(senderAcc, now, TransactionType.TRANSFER, senderAcc, recAcc, amountUSD);
        addTransaction(transactionEntity);
        return true;
    }

    @Override
    public TransactionEntity addTransaction(TransactionEntity transactionEntity) {
        return transactionHistoryRepo.save(transactionEntity);
    }

    private void checkAmount(double amount) throws Exception {
        if (amount < 0) {
            throw new Exception("Incorrect amount");
        }
    }

    private boolean cashOperation(int accNum, double amount, boolean isTransfer, boolean isCashOut, String exception_string, TransactionType transactionType) {
        UserAccountEntity userAccountEntity = userAccRepo.getUserAccountByAccountNumber(accNum); //fetch user account
        double remaining = 0;
        try {
            checkAmount(amount);
            if (isCashOut) {
                remaining = userAccountEntity.getBalance() - amount;   //Deduct money from balance
            } else {
                remaining = userAccountEntity.getBalance() + amount; //Add money to balance
            }
            if (remaining < 0) {
                System.out.println(new Date() + " " + exception_string);
                return false;
            } else {
                userAccountEntity.setBalance(remaining);
                userAccRepo.save(userAccountEntity);
                if (!isTransfer) {
                    TransactionEntity transactionEntity = new TransactionEntity(accNum, now, transactionType, amount);
                    addTransaction(transactionEntity);
                }
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}
