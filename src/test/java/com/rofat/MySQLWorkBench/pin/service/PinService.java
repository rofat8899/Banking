package com.rofat.MySQLWorkBench.pin.service;

import com.rofat.MySQLWorkBench.pin.model.Pin;

import java.util.List;

public interface PinService {
    Pin save(Pin pin);
    Pin findByUserId(int id);

    List<Pin> findAll();
}
