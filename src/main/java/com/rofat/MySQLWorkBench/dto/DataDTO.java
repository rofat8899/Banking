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
    private MerchantEntity merchantEntity;
    private List<PromotionsEntity> promotions;
}
