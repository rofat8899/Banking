package com.rofat.MySQLWorkBench.repository;

import com.rofat.MySQLWorkBench.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepo extends JpaRepository<TransactionEntity,Integer> {
}
