package com.rofat.MySQLWorkBench.model;

import com.rofat.MySQLWorkBench.constant.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TransactionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int createdBy;
    private LocalDateTime localDateTime;
    private TransactionType transactionType;
    private int sendId;
    private int receiverId;
    private double amount;


    public TransactionHistoryEntity(){}

    public TransactionHistoryEntity(int createdBy, LocalDateTime localDateTime, TransactionType transactionType, double amount) {
        this.createdBy = createdBy;
        this.localDateTime = localDateTime;
        this.transactionType = transactionType;
        this.amount = amount;

    }

    public TransactionHistoryEntity(int createdBy, LocalDateTime localDateTime, TransactionType transactionType, int senderId, int receiverId, double amount) {
        this.createdBy = createdBy;
        this.localDateTime = localDateTime;
        this.transactionType = transactionType;
        this.sendId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;

    }
}
