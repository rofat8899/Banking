package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionsRepo extends JpaRepository <PromotionsEntity,Integer> {
    List<PromotionsEntity> findPromotionsByMaid(int id);
}
