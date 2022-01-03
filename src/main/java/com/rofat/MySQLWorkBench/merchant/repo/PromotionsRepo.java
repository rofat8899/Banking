package com.rofat.MySQLWorkBench.merchant.repo;

import com.rofat.MySQLWorkBench.merchant.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionsRepo extends JpaRepository <Promotions,Integer> {
}
