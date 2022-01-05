package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.repository.MerchantRepo;
import com.rofat.MySQLWorkBench.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImp implements MerchantService {
    @Autowired
    private MerchantRepo merchantRepo;

    @Override
    public List<MerchantEntity> getAllMerchant() {
        return merchantRepo.findAll();
    }

    @Override
    public MerchantEntity addMerchant(MerchantEntity merchantEntity) {
        return merchantRepo.save(merchantEntity);
    }

    @Override
    public MerchantEntity getMerchantByMasterId(int id) {
        return merchantRepo.findMerchantByMaId(id);
    }

    @Override
    public MerchantEntity getMerchantByMerchantId(String id) {
        return merchantRepo.findMerchantEntityByMerchantId(id);
    }
}
