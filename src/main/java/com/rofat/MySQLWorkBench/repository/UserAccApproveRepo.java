package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccApproveRepo extends JpaRepository<UserAccountApproveEntity,Integer> {
    UserAccountApproveEntity getUserAccountByAccountNumber(int accountNumber);
}
