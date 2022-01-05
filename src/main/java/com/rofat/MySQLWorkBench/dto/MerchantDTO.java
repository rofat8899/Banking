package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class MerchantDTO {
    private String merchantId;
    private int id;
    private String email;
    private String phone;
    private String name;
    private String secretKey;
    private int masterAccountId;
    private List<UserAccountDTO> settlements;

    public MerchantDTO(MerchantEntity merchantEntity,List<UserAccountEntity> userAccountEntity) {
        this.merchantId = merchantEntity.getMerchantId();
        this.id = merchantEntity.getId();
        this.email = merchantEntity.getEmail();
        this.phone = merchantEntity.getPhone();
        this.name = merchantEntity.getName();
        this.secretKey = merchantEntity.getSecretKey();
        masterAccountId = merchantEntity.getId();
        List<UserAccountDTO> finalSettlement = new ArrayList<>();
        for (UserAccountEntity settlement : userAccountEntity) {
            UserAccountDTO eachUserAccount = new UserAccountDTO(settlement);
            finalSettlement.add(eachUserAccount);
        }
        this.settlements=finalSettlement;
    }
}
