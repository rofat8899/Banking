package com.rofat.MySQLWorkBench.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int securityId;
    private String securityCode;
    private int userId;
    private int numberGenerate;
    private Date modifiedOn;
    private String modifiedBy;
    private Date createdOn;
    private String createdBy;

}
