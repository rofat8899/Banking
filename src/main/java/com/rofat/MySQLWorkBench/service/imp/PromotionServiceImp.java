package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.repository.PromotionsRepo;
import com.rofat.MySQLWorkBench.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {

    @Autowired
    private PromotionsRepo promotionsRepo;

    @Override
    public List<PromotionsEntity> getAllPromotion() {
        return promotionsRepo.findAll();
    }

    @Override
    public PromotionsEntity save(PromotionsEntity promotionsEntity) {
        return promotionsRepo.save(promotionsEntity);
    }

    @Override
    public List<PromotionsEntity> findByMasterId(int maid) {
        return promotionsRepo.findPromotionsByMaid(maid);
    }
}
