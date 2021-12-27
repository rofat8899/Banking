package com.rofat.MySQLWorkBench.pin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String pin;

    private int userId;
}
