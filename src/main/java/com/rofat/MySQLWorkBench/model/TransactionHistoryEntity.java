package com.rofat.MySQLWorkBench.model;

import com.rofat.MySQLWorkBench.constant.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TRANSACTION_HISTORY")
public class TransactionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    @Column(name="CREATED_BY")
    private int createdBy;
    @Column(name="LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="TRANSACTION_TYPE")
    private TransactionType transactionType;
    @Column(name="SENDER_ID")
    private int senderId;
    @Column(name="RECEIVER_ID")
    private int receiverId;
    @Column(name="AMOUNT")
    private double amount;

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
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }
}
