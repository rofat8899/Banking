package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.UserAddressEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserAddressDTO {
    private String village;
    private String commune;
    private String district;
    private String province;
    private String postalCode;

    public UserAddressDTO(UserAddressEntity userAddressEntity) {
        this.village = userAddressEntity.getVillage();
        this.commune = userAddressEntity.getCommune();
        this.district = userAddressEntity.getDistrict();
        this.province = userAddressEntity.getProvince();
        this.postalCode = userAddressEntity.getPostalCode();
    }
}
