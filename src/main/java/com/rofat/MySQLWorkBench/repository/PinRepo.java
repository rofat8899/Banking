package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepo extends JpaRepository<PinEntity,Integer> {
   PinEntity findPinByUserId(String id);
   Boolean existsByUserIdAndPinIsNotNull(String id);
   Boolean existsByPinAndUserId(String pin,int id);
}
