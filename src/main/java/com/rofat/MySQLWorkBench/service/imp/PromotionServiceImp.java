package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.PromotionDTO;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.repository.PromotionsRepo;
import com.rofat.MySQLWorkBench.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {

    @Autowired
    private PromotionsRepo promotionsRepo;

    @Override
    public List<PromotionDTO> getAllPromotion() {
        List<PromotionDTO> promotionDTO = new ArrayList<>();
        for (PromotionsEntity each : promotionsRepo.findAll()) {
            promotionDTO.add(new PromotionDTO(each));
        }
        return promotionDTO;
    }

    @Override
    public PromotionDTO save(PromotionsEntity promotionsEntity) {

        return new PromotionDTO(promotionsRepo.save(promotionsEntity));
    }

    @Override
    public List<PromotionDTO> findByMasterId(int maid) {
        List<PromotionDTO> promotionDTO = new ArrayList<>();
        for (PromotionsEntity each : promotionsRepo.findPromotionsByMaid(maid)) {
            promotionDTO.add(new PromotionDTO(each));
        }
        return promotionDTO;
    }
}
