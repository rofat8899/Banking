package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.dto.PromotionDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.service.DataService;
import com.rofat.MySQLWorkBench.service.MerchantService;
import com.rofat.MySQLWorkBench.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private DataService dataService;

    //Get all Merchant
    @GetMapping("/merchant")
    public List<MerchantEntity> getAllMerchant() {
        return merchantService.getAllMerchant();
    }

    //Insert Merchant
    @PostMapping("/merchant")
    public MerchantEntity addMerchant(@RequestBody MerchantEntity merchantEntity) {
        return merchantService.addMerchant(merchantEntity);
    }

    //Find Merchant by Master ID
    @GetMapping("/merchant/{id}")
    public MerchantEntity getMerchantByMasterId(@PathVariable("id") int id) {
        return merchantService.getMerchantByMasterId(id);
    }

    //Get all Promotion
    @GetMapping("/promotion")
    public List<PromotionDTO> getAllPromotions() {
        return promotionService.getAllPromotion();
    }

    //Insert Promotions with Master ID
    @PostMapping("/promotion/{id}")
    public PromotionDTO insertPromotions(@PathVariable("id") int maId, @RequestBody PromotionsEntity promotionsEntity) {
        promotionsEntity.setMaid(maId);
        return promotionService.save(promotionsEntity);
    }

    //Get Promotion By Master ID
    @GetMapping("/promotion/{id}")
    public List<PromotionDTO> getPromotionByMasterId(@PathVariable("id") int maid) {
        return promotionService.findByMasterId(maid);
    }

    //Get Data by master ID
    @GetMapping("{mid}")
    public DataDTO getDataByMasterId(@PathVariable("mid") int mid) {
        return dataService.getDataByMasterId(mid);
    }
}
