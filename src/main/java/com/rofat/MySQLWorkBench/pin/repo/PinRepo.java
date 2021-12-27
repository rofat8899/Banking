package com.rofat.MySQLWorkBench.pin.repo;

import com.rofat.MySQLWorkBench.pin.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepo extends JpaRepository<Pin,Integer> {
   Pin findPinByUserId(int id);
   Boolean existsByUserIdAndPinIsNotNull(int id);
   Boolean existsByPinAndUserId(String pin,int id);
}
