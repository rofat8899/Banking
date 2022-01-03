package com.rofat.MySQLWorkBench.data.service;

import com.rofat.MySQLWorkBench.data.model.Merchant;
import com.rofat.MySQLWorkBench.data.repo.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImp implements MerchantService{
    @Autowired
    private MerchantRepo merchantRepo;
    @Override
    public List<Merchant> getAllMerchant() {
        return merchantRepo.findAll();
    }

    @Override
    public Merchant addMerchant(Merchant merchant) {
        return merchantRepo.save(merchant);
    }

    @Override
    public Merchant getMerchantByMasterId(int id) {
        return merchantRepo.findMerchantByMaId(id);
    }
}
