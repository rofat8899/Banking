package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.DataDTO;

public interface DataService {
    DataDTO getDataByMasterId(int id);
}
