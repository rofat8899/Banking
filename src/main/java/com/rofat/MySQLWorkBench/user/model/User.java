package com.rofat.MySQLWorkBench.user.model;

import com.rofat.MySQLWorkBench.user.constant.Role;
import com.rofat.MySQLWorkBench.user.constant.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
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
    private UserAddress address; //Address Table
    @OneToOne(cascade = CascadeType.ALL)
    private UserContact contact; //Contact Table

}
