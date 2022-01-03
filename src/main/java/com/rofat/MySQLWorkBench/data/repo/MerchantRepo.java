package com.rofat.MySQLWorkBench.data.repo;

import com.rofat.MySQLWorkBench.data.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepo extends JpaRepository<Merchant,Integer> {

    Merchant findMerchantByMaId(int id);
}
