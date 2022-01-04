package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROMOTION")
public class PromotionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    @Column(name="MASTER_ACCOUNT_ID")
    private int maid;
    @OneToOne(cascade = CascadeType.ALL)
    private DurationEntity durationEntity;
    @Column(name="MIN_PAYMENT")
    private String minPayment;
    @Column(name="MAX_PAYMENT")
    private String maxPayment;
    @Column(name="PROMOTION_TYPE")
    private String promotionType;
    @Column(name="PROMOTION_VALUE_TYPE")
    private String promotionValueType;
    @Column(name="PROMOTION_AMOUNT")
    private String promotionAmount;


}
