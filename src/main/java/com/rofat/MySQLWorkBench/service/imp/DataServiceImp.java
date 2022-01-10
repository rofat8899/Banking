package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.repository.PromotionsRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.DataService;
import com.rofat.MySQLWorkBench.service.MerchantService;
import com.rofat.MySQLWorkBench.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp implements DataService {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PromotionsRepo promotionsRepo;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccRepo userAccRepo;

    @Override
    public DataDTO getDataByMasterId(int mid) {
        MerchantEntity merchantEntity = merchantService.getMerchantByMasterId(mid);
        List<PromotionsEntity> promotionsEntity = promotionsRepo.findPromotionsByMaid(mid);
        List<UserAccountEntity> userAccountEntity = userAccRepo.getUserAccountByMaId(mid);
        return new DataDTO(merchantEntity,userAccountEntity, promotionsEntity);
    }
}
