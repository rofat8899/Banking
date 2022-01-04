package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="MERCHANT_SETTLEMEMT")
public class MerchantSettlementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="MERCHANT_MASTER_ACCOUNT_ID")
    private int merchantMasterId;
    @Column(name="USER_ACCOUNT_ID")
    private int SettlementsId;

    public MerchantSettlementsEntity(int mmid, int sid) {
        this.merchantMasterId=mmid;
        this.SettlementsId=sid;
    }
}
