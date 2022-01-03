package com.rofat.MySQLWorkBench.data.repo;

import com.rofat.MySQLWorkBench.data.model.MerchantSettlements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantSettlementRepo extends JpaRepository<MerchantSettlements,Integer> {
}
