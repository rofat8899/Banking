package com.rofat.MySQLWorkBench.merchant.repo;

import com.rofat.MySQLWorkBench.merchant.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepo extends JpaRepository<Merchant,Integer> {

    Merchant findMerchantByMaId(int id);
}
