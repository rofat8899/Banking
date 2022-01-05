package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.constant.Role;
import com.rofat.MySQLWorkBench.constant.Sex;
import com.rofat.MySQLWorkBench.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private int masterAccountID;
    private String userId;
    private String name;
    private Sex sex;
    private int age;
    private int defaultAccount;
    private Role role;
    private UserAddressDTO address;
    private UserContactDTO contact;

    public UserDTO(UserEntity userEntity) {
        this.masterAccountID = userEntity.getMaId();
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.sex = userEntity.getSex();
        this.age = userEntity.getAge();
        this.defaultAccount = userEntity.getDefaultAccount();
        this.role = userEntity.getRole();
        this.address = new UserAddressDTO(userEntity.getAddress());
        this.contact = new UserContactDTO(userEntity.getContact());
    }
}
