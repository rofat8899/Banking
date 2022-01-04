package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.PromotionsEntity;

import java.util.List;

public interface PromotionService {
    List<PromotionsEntity> getAllPromotion();

    PromotionsEntity save(PromotionsEntity promotionsEntity);

    List<PromotionsEntity> findByMasterId(int maid);
}
