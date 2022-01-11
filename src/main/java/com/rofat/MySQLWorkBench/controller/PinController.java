package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.PinCodeDTO;
import com.rofat.MySQLWorkBench.dto.ResponseMessageDTO;
import com.rofat.MySQLWorkBench.model.PinEntity;
import com.rofat.MySQLWorkBench.service.PinService;
import com.rofat.MySQLWorkBench.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pin")
public class PinController {

    @Autowired
    private PinService pinService;
    @Autowired
    private UserService userService;

    //Create Pin with UserID
    @PostMapping("/{id}")
    public PinCodeDTO addPin(@RequestBody PinEntity pinEntity, @PathVariable String id) {
        pinEntity.setUserId(id);
        return pinService.save(pinEntity);
    }

    //Get All User Pin
    @GetMapping("")
    public List<PinCodeDTO> getAllUserPin() {
        return pinService.findAll();
    }

//    @GetMapping("/validation")      //validate pin
//    public Boolean userValidation(@RequestBody Map<String, Object> obj) {
//        String userId = (String) obj.get("userId");
//        String userPin = (String) obj.get("userPin");
//        return userService.validation(userId, userPin);
//    }

    @GetMapping("/validation")      //validate pin
    public ResponseMessageDTO userValidation(@RequestBody Map<String, Object> obj) {
        return pinService.validatePinCode(obj);
    }
}
