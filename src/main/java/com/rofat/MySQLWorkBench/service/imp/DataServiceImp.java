package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.service.DataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp implements DataService {

    @Override
    public DataDTO getDataByMasterId(MerchantEntity merchantByMasterId, List<PromotionsEntity> promotionByMasterId) {
        return new DataDTO(merchantByMasterId,promotionByMasterId);
    }
}
