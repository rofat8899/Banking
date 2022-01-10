package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionDTO {
    private DurationDTO duration;
    private String minPayment;
    private String maxPayment;
    private String promotionType;
    private String promotionValueType;
    private String promotionAmount;
    public PromotionDTO(PromotionsEntity promotionsEntity){
        this.duration = new DurationDTO(promotionsEntity.getDurationEntity());
        this.minPayment = promotionsEntity.getMinPayment();
        this.maxPayment = promotionsEntity.getMaxPayment();
        this.promotionType = promotionsEntity.getPromotionType();
        this.promotionValueType = promotionsEntity.getPromotionValueType();
        this.promotionAmount = promotionsEntity.getPromotionAmount();
    }
}
