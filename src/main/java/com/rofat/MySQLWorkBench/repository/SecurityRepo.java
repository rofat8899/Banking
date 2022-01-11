package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.SecureCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepo extends JpaRepository<SecureCodeEntity,Integer> {
    SecureCodeEntity getSecurityByUserId(String id);
    Boolean existsByUserId(String id);

}
