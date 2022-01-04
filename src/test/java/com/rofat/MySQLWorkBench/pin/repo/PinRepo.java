package com.rofat.MySQLWorkBench.pin.repo;

import com.rofat.MySQLWorkBench.model.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepo extends JpaRepository<PinEntity,Integer> {
   PinEntity findPinByUserId(int id);
}
