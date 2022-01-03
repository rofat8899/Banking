package com.rofat.MySQLWorkBench.data.repo;

import com.rofat.MySQLWorkBench.data.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionsRepo extends JpaRepository <Promotions,Integer> {
}
