package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PromotionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int maid;
    @OneToOne(cascade = CascadeType.ALL)
    private DurationEntity durationEntity;
    private String minPayment;
    private String maxPayment;
    private String promotionType;
    private String promotionValueType;
    private String promotionAmount;


}
