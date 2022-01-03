package com.rofat.MySQLWorkBench.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Duration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String start;
    private String end;
}
