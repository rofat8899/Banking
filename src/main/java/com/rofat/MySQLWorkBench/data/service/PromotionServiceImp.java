package com.rofat.MySQLWorkBench.data.service;

import com.rofat.MySQLWorkBench.data.model.Promotions;
import com.rofat.MySQLWorkBench.data.repo.PromotionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService{

    @Autowired
    private PromotionsRepo promotionsRepo;

    @Override
    public List<Promotions> getAllPromotion() {
        return promotionsRepo.findAll();
    }

    @Override
    public Promotions save(Promotions promotions) {
        return promotionsRepo.save(promotions);
    }

    @Override
    public List<Promotions> findByMasterId(int maid) {
        return promotionsRepo.findPromotionsByMaid(maid);
    }
}
