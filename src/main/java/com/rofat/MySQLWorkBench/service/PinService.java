package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.PinCodeDTO;
import com.rofat.MySQLWorkBench.model.PinEntity;

import java.util.List;

public interface PinService {
    PinCodeDTO save(PinEntity pinEntity);
    PinCodeDTO findByUserId(String id);

    List<PinCodeDTO> findAll();
}
