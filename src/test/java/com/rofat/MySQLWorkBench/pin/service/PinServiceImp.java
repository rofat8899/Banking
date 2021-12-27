package com.rofat.MySQLWorkBench.pin.service;

import com.rofat.MySQLWorkBench.pin.model.Pin;
import com.rofat.MySQLWorkBench.pin.repo.PinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinServiceImp implements PinService{
    @Autowired
    private PinRepo pinRepo;

    @Override
    public Pin save(Pin pin) {
        return pinRepo.save(pin);
    }

    @Override
    public Pin findByUserId(int id) {
        return pinRepo.findPinByUserId(id);
    }

    @Override
    public List<Pin> findAll() {
        return pinRepo.findAll();
    }
}
