package com.rofat.MySQLWorkBench.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int maid;
    @OneToOne(cascade = CascadeType.ALL)
    private Duration duration;
    private String minPayment;
    private String maxPayment;
    private String promotionType;
    private String promotionValueType;
    private String promotionAmount;


}
