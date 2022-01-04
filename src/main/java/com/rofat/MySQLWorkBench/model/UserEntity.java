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
@Table(name = "USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="MASTER_ACCOUNT_ID")
    private int maId;
    @Column(name="USER_ID")
    private int userId;
    @Column(name="NAME")
    private String name;
    @Column(name="SEX")
    private Sex sex;
    @Column(name="AGE")
    private int age;
    @Column(name="DEFAULT_ACCOUNT")
    private int defaultAccount;
    @Column(name="ROLE")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private UserAddressEntity address;
    @OneToOne(cascade = CascadeType.ALL)
    private UserContactEntity contact;

}
