package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.PromotionDTO;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;

import java.util.List;

public interface PromotionService {
    List<PromotionDTO> getAllPromotion();

    PromotionDTO save(PromotionsEntity promotionsEntity);

    List<PromotionDTO> findByMasterId(int maid);
}
