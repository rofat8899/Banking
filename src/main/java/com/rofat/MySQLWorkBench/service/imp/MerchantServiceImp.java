package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.MerchantDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.repository.MerchantRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceImp implements MerchantService {
    @Autowired
    private MerchantRepo merchantRepo;
    @Autowired
    private UserAccRepo userAccRepo;

    @Override
    public List<MerchantDTO> getAllMerchant() {
        List<MerchantDTO> merchantDTO = new ArrayList<>();
        for (MerchantEntity each : merchantRepo.findAll()) {
            merchantDTO.add(new MerchantDTO(each));
        }
        return merchantDTO;
    }

    @Override
    public MerchantDTO addMerchant(MerchantEntity merchantEntity) {
        return new MerchantDTO(merchantRepo.save(merchantEntity));
    }

    @Override
    public MerchantDTO getMerchantByMasterId(int id) {
        return new MerchantDTO(merchantRepo.findMerchantByMaId(id), userAccRepo.getUserAccountByMaId(id));
    }

    @Override
    public MerchantDTO getMerchantByMerchantId(String id) {
        MerchantEntity merchantEntity = merchantRepo.findMerchantEntityByMerchantId(id);
        return new MerchantDTO(merchantEntity, userAccRepo.getUserAccountByMaId(merchantEntity.getMaId()));
    }
}
