package com.rofat.MySQLWorkBench.merchant.service;

import com.rofat.MySQLWorkBench.merchant.model.Merchant;

import java.util.List;

public interface MerchantService {
    List<Merchant> getAllMerchant();

    Merchant addMerchant(Merchant merchant);

    Merchant getMerchantByMasterId(int id);
}
