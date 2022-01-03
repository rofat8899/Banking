package com.rofat.MySQLWorkBench.data.repo;

import com.rofat.MySQLWorkBench.data.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionsRepo extends JpaRepository <Promotions,Integer> {
    List<Promotions> findPromotionsByMaid(int id);
}
