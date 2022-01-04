package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_CONTACT")
public class UserContactEntity {
    @Id
    @Column(name="MASTER_ACCOUNT_ID")
    private int maId;               //Master Account ID
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;     //Phone Number
    @Column(name="EMAIL")
    private String email;           //Email
}
