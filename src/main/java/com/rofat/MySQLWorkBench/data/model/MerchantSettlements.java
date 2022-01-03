package com.rofat.MySQLWorkBench.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="merchant_settlements")
public class MerchantSettlements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="merchant_master_id")
    private int merchantMasterId;
    @Column(name="settlements_id")
    private int SettlementsId;

    public MerchantSettlements(int mmid, int sid) {
        this.merchantMasterId=mmid;
        this.SettlementsId=sid;
    }
}
