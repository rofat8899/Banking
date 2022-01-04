package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepo extends JpaRepository<MerchantEntity,Integer> {

    MerchantEntity findMerchantByMaId(int id);
}
