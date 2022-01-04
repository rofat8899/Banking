package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "USER_ADDRESS")
public class UserAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="VILLAGE")
    private String village;
    @Column(name="COMMUNE")
    private String commune;
    @Column(name="DISTRICT")
    private String district;
    @Column(name="PROVINCE")
    private String province;
    @Column(name="POSTAL_CODE")
    private String postalCode;
}
