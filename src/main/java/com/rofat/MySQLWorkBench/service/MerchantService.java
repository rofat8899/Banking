package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.MerchantDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;

import java.util.List;

public interface MerchantService {
    List<MerchantDTO> getAllMerchant();

    MerchantDTO addMerchant(MerchantEntity merchantEntity);

    MerchantDTO getMerchantByMasterId(int id);

    MerchantDTO getMerchantByMerchantId(String id);
}
