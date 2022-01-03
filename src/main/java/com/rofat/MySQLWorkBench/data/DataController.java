package com.rofat.MySQLWorkBench.data;

import com.rofat.MySQLWorkBench.data.model.Merchant;
import com.rofat.MySQLWorkBench.data.model.Promotions;
import com.rofat.MySQLWorkBench.data.service.MerchantService;
import com.rofat.MySQLWorkBench.data.service.PromotionService;
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


    //Get all Merchant
    @GetMapping("/merchant")
    public List<Merchant> getAllMerchant(){
        return merchantService.getAllMerchant();
    }
    //Insert Merchant
    @PostMapping("/merchant")
    public Merchant addMerchant(@RequestBody Merchant merchant){
        return merchantService.addMerchant(merchant);
    }
    //Find Merchant by Master ID
    @GetMapping("/merchant/{id}")
    public Merchant getMerchantByMasterId(@PathVariable("id") int id)
    {
        return merchantService.getMerchantByMasterId(id);
    }

    //Get all Promotion
    @GetMapping("/promotions")
    public List<Promotions> getAllPromotions(){
        return promotionService.getAllPromotion();
    }

    //Insert Promotions with Master ID
    @PostMapping("/promotions/{id}")
    public Promotions insertPromotions(@PathVariable("id") int maId , @RequestBody Promotions promotions)
    {
        promotions.setMaid(maId);
        return promotionService.save(promotions);
    }
}
