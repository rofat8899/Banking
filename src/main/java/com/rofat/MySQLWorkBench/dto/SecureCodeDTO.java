package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.SecureCodeEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SecureCodeDTO {
    private String securityCode;
    private String userId;
    private int numberGenerate;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private LocalDateTime createdOn;
    private String createdBy;
    public SecureCodeDTO(String code, SecureCodeEntity secureCodeEntity){
        this.securityCode= code;
        this.userId = secureCodeEntity.getUserId();
        this.numberGenerate = secureCodeEntity.getNumberGenerate();
        this.modifiedOn = secureCodeEntity.getModifiedOn();
        this.modifiedBy = secureCodeEntity.getModifiedBy();
        this.createdOn = secureCodeEntity.getCreatedOn();
        this.createdBy = secureCodeEntity.getCreatedBy();
    }
}
