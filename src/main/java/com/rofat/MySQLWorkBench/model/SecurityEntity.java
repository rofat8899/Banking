package com.rofat.MySQLWorkBench.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SECURITY")
public class SecurityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SECURITY_ID")
    private int securityId;
    @Column(name="SECURITY_CODE")
    private String securityCode;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="NUMBER_GENERATE")
    private int numberGenerate;
    @Column(name="MODIFIED_ON")
    private LocalDateTime modifiedOn;
    @Column(name="MODIFIED_BY")
    private String modifiedBy;
    @Column(name="CREATED_ON")
    private LocalDateTime createdOn;
    @Column(name="CREATED_BY")
    private String createdBy;

}
