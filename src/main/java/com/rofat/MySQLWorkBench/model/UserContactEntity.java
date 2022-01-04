package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserContactEntity {
    @Id
    private int maId;               //Master Account ID
    private String phoneNumber;     //Phone Number
    private String email;           //Email
}
