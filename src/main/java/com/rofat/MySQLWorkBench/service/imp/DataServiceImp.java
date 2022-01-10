package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.DataDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.repository.MerchantRepo;
import com.rofat.MySQLWorkBench.repository.PromotionsRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp implements DataService {

    @Autowired
    private MerchantRepo merchantRepo;
    @Autowired
    private PromotionsRepo promotionsRepo;
    @Autowired
    private UserAccRepo userAccRepo;

    @Override
    public DataDTO getDataByMasterId(int mid) {
        MerchantEntity merchantEntity = merchantRepo.findMerchantByMaId(mid);
        List<PromotionsEntity> promotionsEntity = promotionsRepo.findPromotionsByMaid(mid);
        List<UserAccountEntity> userAccountEntity = userAccRepo.getUserAccountByMaId(mid);
        return new DataDTO(merchantEntity,userAccountEntity, promotionsEntity);
    }
}
