package com.rofat.MySQLWorkBench.merchant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Promotions {
    @Id
    private int id;
    private String name;
}
