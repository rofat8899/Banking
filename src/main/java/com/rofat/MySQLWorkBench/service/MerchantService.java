package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.model.MerchantEntity;

import java.util.List;

public interface MerchantService {
    List<MerchantEntity> getAllMerchant();

    MerchantEntity addMerchant(MerchantEntity merchantEntity);

    MerchantEntity getMerchantByMasterId(int id);
}
