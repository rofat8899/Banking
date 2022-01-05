package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PinController {

    @Autowired
    PinService pinService;

    //Create Pin with UserID
    @PostMapping("/pin/{id}")
    public PinEntity addPin(@RequestBody PinEntity pinEntity, @PathVariable String id) {
        pinEntity.setUserId(id);
        return pinService.save(pinEntity);
    }

    //Get All User Pin
    @GetMapping("/pin")
    public List<PinEntity> getAllUserPin() {
        return pinService.findAll();
    }
}
