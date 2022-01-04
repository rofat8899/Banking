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
    private int MasterAccountId;
    private List<UserAccountDTO> settlements;

    public MerchantDTO(MerchantEntity merchantByMasterId) {
        this.merchantId = merchantByMasterId.getMerchantId();
        this.id = merchantByMasterId.getId();
        this.email = merchantByMasterId.getEmail();
        this.phone = merchantByMasterId.getPhone();
        this.name = merchantByMasterId.getName();
        this.secretKey = merchantByMasterId.getSecretKey();
        MasterAccountId = merchantByMasterId.getId();
        List<UserAccountDTO> finalSettlement = new ArrayList<>();
        for (UserAccountEntity settlement : merchantByMasterId.getSettlements()) {
            UserAccountDTO eachUserAccount = new UserAccountDTO(settlement);
            finalSettlement.add(eachUserAccount);
        }
        this.settlements=finalSettlement;
    }
}
