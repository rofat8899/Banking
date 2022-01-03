package com.rofat.MySQLWorkBench.merchant.repo;

import com.rofat.MySQLWorkBench.merchant.model.MerchantSettlements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantSettlementRepo extends JpaRepository<MerchantSettlements,Integer> {
}
