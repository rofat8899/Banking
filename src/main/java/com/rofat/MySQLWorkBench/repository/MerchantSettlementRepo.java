package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.MerchantSettlementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantSettlementRepo extends JpaRepository<MerchantSettlementsEntity,Integer> {
}
