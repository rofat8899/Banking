package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.UserContactDTO;
import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.UserContactEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.service.UserContactService;
import com.rofat.MySQLWorkBench.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserContactService userContactService;

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping()
    public UserDTO addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByMasterAccId(@PathVariable Integer id) {
        try {
            UserDTO user = userService.getUserByMasterAccId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/uid/{id}")
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable String id) {
        try {
            UserDTO user = userService.getUserByUserId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contact/{uid}")
    public UserContactDTO addUserContact(@RequestBody UserContactEntity userContact, @PathVariable("uid") Integer id) {
        userContact.setMaId(id);
        return userContactService.addUserContact(userContact);
    }

    @PostMapping("/account/default")
    public UserDTO setDefaultAccount(@RequestBody Map<String, Object> obj) {
        int maId = (int) obj.get("maId");
        int defaultAccount = (int) obj.get("defaultAccount");
        return userService.setDefaultAccount(maId, defaultAccount);
    }
}
