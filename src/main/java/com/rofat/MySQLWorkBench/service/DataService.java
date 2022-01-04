package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;

import java.util.List;

public interface DataService {
    DataDTO getDataByMasterId(MerchantEntity merchantByMasterId, List<PromotionsEntity> byMasterId);
}
