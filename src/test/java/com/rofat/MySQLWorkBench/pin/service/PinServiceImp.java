package com.rofat.MySQLWorkBench.pin.service;

import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.repository.PinRepo;
import com.rofat.MySQLWorkBench.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinServiceImp implements PinService {
    @Autowired
    private PinRepo pinRepo;

    @Override
    public PinEntity save(PinEntity pinEntity) {
        return pinRepo.save(pinEntity);
    }

    @Override
    public PinEntity findByUserId(int id) {
        return pinRepo.findPinByUserId(id);
    }

    @Override
    public List<PinEntity> findAll() {
        return pinRepo.findAll();
    }
}
