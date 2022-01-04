package com.rofat.MySQLWorkBench.model;

import com.rofat.MySQLWorkBench.constant.Role;
import com.rofat.MySQLWorkBench.constant.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int maId;       //Master Account ID

    private int userId;     //User ID

    private String name;    //Nam

    private Sex sex;        //Sex

    private int age;        //Age

    private int defaultAccount; // default account

    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private UserAddressEntity address; //Address Table
    @OneToOne(cascade = CascadeType.ALL)
    private UserContactEntity contact; //Contact Table

}
