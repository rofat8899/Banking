package com.rofat.MySQLWorkBench.pin;

import com.rofat.MySQLWorkBench.pin.model.Pin;
import com.rofat.MySQLWorkBench.pin.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class pinController {

    @Autowired
    PinService pinService;

    @PostMapping("/pin/{id}")
    public Pin addPin(@RequestBody Pin pin, @PathVariable Integer id)
    {
        pin.setUserId(id);
        return  pinService.save(pin);
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
    public List<Pin> getAllUserPin(){
        return pinService.findAll();
    }
}
