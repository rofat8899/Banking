package com.rofat.MySQLWorkBench.data.service;

import com.rofat.MySQLWorkBench.data.model.Merchant;

import java.util.List;

public interface MerchantService {
    List<Merchant> getAllMerchant();

    Merchant addMerchant(Merchant merchant);

    Merchant getMerchantByMasterId(int id);
}
