package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="MERCHANT_SETTLEMENTS")
public class MerchantSettlementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="MERCHANT_ENTITY_MASTER_ACCOUNT_ID")
    private int merchantMasterId;
    @Column(name="SETTLEMENTS_ID")
    private int SettlementsId;

    public MerchantSettlementsEntity(int mid, int sid) {
        this.merchantMasterId=mid;
        this.SettlementsId=sid;
    }
}
