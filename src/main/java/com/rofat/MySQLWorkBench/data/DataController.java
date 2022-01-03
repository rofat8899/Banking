package com.rofat.MySQLWorkBench.data;

import com.rofat.MySQLWorkBench.data.model.Data;
import com.rofat.MySQLWorkBench.data.model.Merchant;
import com.rofat.MySQLWorkBench.data.model.Promotions;
import com.rofat.MySQLWorkBench.data.service.MerchantService;
import com.rofat.MySQLWorkBench.data.service.PromotionService;
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
    @GetMapping("/promotion")
    public List<Promotions> getAllPromotions(){
        return promotionService.getAllPromotion();
    }

    //Insert Promotions with Master ID
    @PostMapping("/promotion/{id}")
    public Promotions insertPromotions(@PathVariable("id") int maId , @RequestBody Promotions promotions)
    {
        promotions.setMaid(maId);
        return promotionService.save(promotions);
    }
    //Get Promotion By Master ID
    @GetMapping("/promotion/{id}")
    public List<Promotions> getPromotionByMasterId(@PathVariable("id") int maid){
        return promotionService.findByMasterId(maid);
    }

    //Get Data by master ID
    @GetMapping("{mid}")
    public Dictionary getDataByMasterId(@PathVariable("mid") int mid)
    {
        Data data = new Data();
        data.setMerchant(merchantService.getMerchantByMasterId(mid));
        data.setPromotions(promotionService.findByMasterId(mid));
        Dictionary result = new Hashtable();
        result.put("data", data);
        return result;
    }
}
