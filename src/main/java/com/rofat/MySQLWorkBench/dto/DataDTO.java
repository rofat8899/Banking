package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DataDTO {
    private MerchantDTO merchant;
    private List<PromotionDTO> promotions;

    public DataDTO(MerchantEntity merchantByMasterId, List<PromotionsEntity> promotionByMasterId) {
        this.merchant = new MerchantDTO(merchantByMasterId);
        List<PromotionDTO> finalPromotions = new ArrayList<>();
        for (PromotionsEntity each : promotionByMasterId) {
            PromotionDTO promotionDTO = new PromotionDTO(each);
            finalPromotions.add(promotionDTO);
        }
        this.promotions = finalPromotions;
    }

}
