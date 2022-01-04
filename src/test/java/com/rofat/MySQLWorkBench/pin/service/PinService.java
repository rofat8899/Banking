package com.rofat.MySQLWorkBench.pin.service;

import com.rofat.MySQLWorkBench.model.PinEntity;

import java.util.List;

public interface PinService {
    PinEntity save(PinEntity pinEntity);
    PinEntity findByUserId(int id);

    List<PinEntity> findAll();
}
