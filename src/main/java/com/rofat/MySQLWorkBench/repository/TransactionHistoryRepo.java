package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepo extends JpaRepository<TransactionHistoryEntity,Integer> {
}
