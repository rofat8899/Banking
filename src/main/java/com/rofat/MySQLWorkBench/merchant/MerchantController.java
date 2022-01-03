package com.rofat.MySQLWorkBench.merchant;

import com.rofat.MySQLWorkBench.merchant.model.Merchant;
import com.rofat.MySQLWorkBench.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    //Get all Merchant
    @GetMapping()
    public List<Merchant> getAllMerchant(){
        return merchantService.getAllMerchant();
    }
    //Insert Merchant
    @PostMapping()
    public Merchant addMerchant(@RequestBody Merchant merchant){
        return merchantService.addMerchant(merchant);
    }
    //Find Merchant by Master ID
    @GetMapping("/{id}")
    public Merchant getMerchantByMasterId(@PathVariable("id") int id)
    {
        return merchantService.getMerchantByMasterId(id);
    }
}
