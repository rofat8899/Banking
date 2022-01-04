package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity getUserByUserId(Integer id);
    UserEntity getUserByMaId(Integer id);
    Boolean existsByUserId(Integer id);

}
