package com.rofat.MySQLWorkBench.data.service;

import com.rofat.MySQLWorkBench.data.model.Promotions;

import java.util.List;

public interface PromotionService {
    List<Promotions> getAllPromotion();

    Promotions save(Promotions promotions);

    List<Promotions> findByMasterId(int maid);
}
