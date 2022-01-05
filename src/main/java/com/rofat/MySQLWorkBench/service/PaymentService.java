package com.rofat.MySQLWorkBench.service;

import com.rofat.MySQLWorkBench.dto.PaymentDTO;

import java.text.ParseException;
import java.util.Map;

public interface PaymentService {
    PaymentDTO payment(Map<String, Object> request) throws ParseException;
}
