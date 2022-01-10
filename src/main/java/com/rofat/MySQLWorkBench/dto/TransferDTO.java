package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransferDTO {

    private LocalDateTime createdOn;

    private String senderName;

    private String receiverName;

    private double amountKHR;

    private double amountUSD;

    private String message;

    public TransferDTO(double amountUSD, double amountKHR, UserEntity sender , UserEntity receiver, LocalDateTime dateTime, boolean isSucceed){
        this.createdOn = dateTime;
        this.senderName = sender.getName();
        this.receiverName = receiver.getName();
        this.amountKHR = amountKHR;
        this.amountUSD = amountUSD;
        if(isSucceed)
        {
            this.message="transfer success";
        }
        else
        {
            this.message = "transfer failed";
        }
    }

}
