package com.rofat.MySQLWorkBench.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="MERCHANT")
public class MerchantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MASTER_ACCOUNT_ID")
    private int maId;
    @Column(name="ID")
    private int id;
    @Column(name="MERCHANT_ID")
    private String merchantId;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PHONE")
    private String phone;
    @Column(name="NAME")
    private String name;
    @Column(name="SECRET_KEY")
    private String secretKey;

    @OneToMany(targetEntity = UserAccountEntity.class,cascade = CascadeType.ALL)
    private List<UserAccountEntity> settlements;

}
