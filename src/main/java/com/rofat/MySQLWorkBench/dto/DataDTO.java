package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DataDTO {
    private MerchantDTO merchant;
    public DataDTO(MerchantEntity merchantByMasterId, List<PromotionsEntity> promotionByMasterId) {
        this.merchant = new MerchantDTO(merchantByMasterId);
    }
    //private List<PromotionsEntity> promotions;
}
