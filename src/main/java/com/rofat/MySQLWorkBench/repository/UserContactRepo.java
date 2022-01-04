package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.UserContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepo extends JpaRepository<UserContactEntity,Integer> {
    UserContactEntity findUserContactByMaId(Integer id);
}
