package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.UserContactEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserContactDTO {
    private String phoneNumber;
    private String email;
    public UserContactDTO(UserContactEntity userContactEntity){
        this.phoneNumber=userContactEntity.getPhoneNumber();
        this.email=userContactEntity.getEmail();
    }

}
