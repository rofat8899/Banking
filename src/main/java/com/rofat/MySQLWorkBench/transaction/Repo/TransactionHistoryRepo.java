package com.rofat.MySQLWorkBench.transaction.Repo;

import com.rofat.MySQLWorkBench.transaction.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepo extends JpaRepository<TransactionHistory,Integer> {
}
