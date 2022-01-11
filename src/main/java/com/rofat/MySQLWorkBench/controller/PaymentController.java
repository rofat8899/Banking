package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.PaymentDTO;
import com.rofat.MySQLWorkBench.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment-gateway")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public PaymentDTO payment(@RequestBody Map<String, Object> request) throws Exception {
        return paymentService.payment(request);
    }
}
