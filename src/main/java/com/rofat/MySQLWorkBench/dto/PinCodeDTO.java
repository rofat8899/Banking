package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.PinEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinCodeDTO {
    private String pinCode;
    private String userId;
    public PinCodeDTO(PinEntity pinEntity){
        this.pinCode = pinEntity.getPin();
        this.userId = pinEntity.getUserId();
    }
}
