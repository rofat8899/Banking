package com.rofat.MySQLWorkBench.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private LocalDateTime createdOn;
    private String createdBy;

}
