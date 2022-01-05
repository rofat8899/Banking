package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.service.DataService;
import com.rofat.MySQLWorkBench.service.MerchantService;
import com.rofat.MySQLWorkBench.service.PromotionService;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp implements DataService {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public DataDTO getDataByMasterId(int mid) {
        MerchantEntity merchantEntity = merchantService.getMerchantByMasterId(mid);
        List<PromotionsEntity> promotionsEntity = promotionService.findByMasterId(mid);
        List<UserAccountEntity> userAccountEntity = userAccountService.getUserAccountByMasterAccId(mid);
        return new DataDTO(merchantEntity,userAccountEntity, promotionsEntity);
    }
}
