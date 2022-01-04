package com.rofat.MySQLWorkBench.pin;

import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class pinController {

    @Autowired
    PinService pinService;

    @PostMapping("/pin/{id}")
    public PinEntity addPin(@RequestBody PinEntity pinEntity, @PathVariable Integer id)
    {
        pinEntity.setUserId(id);
        return  pinService.save(pinEntity);
    }
//    @PostMapping("/pin/{id}")
//    public ResponseEntity<Pin> addPin(@RequestBody Pin pin, @PathVariable int id)
//    {
//        pin.setUserId(id);
//        Pin tempPin = pinService.findByUserId(id);
//        if(tempPin.getUserId()==id)
//        {
//            return new ResponseEntity<>(pin, HttpStatus.BAD_REQUEST);
//        }
//       else
//        {
//            pinService.save(pin);
//            return new ResponseEntity<>(pin,HttpStatus.CREATED);
//        }
//    }

    @GetMapping("/pin")
    public List<PinEntity> getAllUserPin(){
        return pinService.findAll();
    }
}
