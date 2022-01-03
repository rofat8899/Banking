package com.rofat.MySQLWorkBench.pin;

import com.rofat.MySQLWorkBench.pin.model.Pin;
import com.rofat.MySQLWorkBench.pin.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PinController {

    @Autowired
    PinService pinService;

    @PostMapping("/pin/{id}")
    public Pin addPin(@RequestBody Pin pin, @PathVariable Integer id)
    {

        pin.setUserId(id);
        return  pinService.save(pin);
    }

    @GetMapping("/pin")
    public List<Pin> getAllUserPin(){
        return pinService.findAll();
    }
}
