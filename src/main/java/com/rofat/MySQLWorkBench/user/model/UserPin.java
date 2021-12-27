package com.rofat.MySQLWorkBench.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserPin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         //ID

    private String pin;     //Pin

    private int userId;     //User ID
}
