package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.service.MerchantService;
import com.rofat.MySQLWorkBench.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PromotionService promotionService;

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
    public List<PromotionsEntity> getAllPromotions() {
        return promotionService.getAllPromotion();
    }

    //Insert Promotions with Master ID
    @PostMapping("/promotion/{id}")
    public PromotionsEntity insertPromotions(@PathVariable("id") int maId, @RequestBody PromotionsEntity promotionsEntity) {
        promotionsEntity.setMaid(maId);
        return promotionService.save(promotionsEntity);
    }

    //Get Promotion By Master ID
    @GetMapping("/promotion/{id}")
    public List<PromotionsEntity> getPromotionByMasterId(@PathVariable("id") int maid) {
        return promotionService.findByMasterId(maid);
    }

    //Get Data by master ID
    @GetMapping("{mid}")
    public Dictionary getDataByMasterId(@PathVariable("mid") int mid) {
        DataDTO data = new DataDTO();
        data.setMerchantEntity(merchantService.getMerchantByMasterId(mid));
        data.setPromotions(promotionService.findByMasterId(mid));
        Dictionary result = new Hashtable();
        result.put("data", data);
        return result;
    }
}
