package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepo extends JpaRepository<SecurityEntity,Integer> {
    SecurityEntity getSecurityByUserId(Integer id);
    Boolean existsByUserId(Integer id);

}
