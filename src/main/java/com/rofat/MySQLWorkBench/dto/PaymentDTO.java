package com.rofat.MySQLWorkBench.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PaymentDTO {
    private LocalDateTime createdOn;
    private String payTo;
    private String payBy;
    private String promotionType;
    private String promotionAmount;
    private double promotionTotal;
    private double amount;
    private String message;
}
