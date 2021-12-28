package com.rofat.MySQLWorkBench.security.repo;

import com.rofat.MySQLWorkBench.security.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepo extends JpaRepository<Security,Integer> {
    Security getSecurityByUserId(Integer id);
    Boolean existsByUserId(Integer id);
}
