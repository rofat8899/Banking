package com.rofat.MySQLWorkBench.controller;

import com.rofat.MySQLWorkBench.dto.UserAccountDTO;
import com.rofat.MySQLWorkBench.dto.UserContactDTO;
import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.UserAccountApproveEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.model.UserContactEntity;
import com.rofat.MySQLWorkBench.model.UserEntity;
import com.rofat.MySQLWorkBench.service.UserAccountApproveService;
import com.rofat.MySQLWorkBench.service.UserAccountService;
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
    private UserAccountService userAccountService;
    @Autowired
    private UserContactService userContactService;
    @Autowired
    private UserAccountApproveService userAccountApproveService;

    //User
    @GetMapping()               //Get All Users
    public List<UserEntity> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping()              //Insert User
    public UserDTO addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")          //Get User By Master Account ID
    public ResponseEntity<UserDTO> getUserByMasterAccId(@PathVariable Integer id) {
        try {
            UserDTO user = userService.getUserByMasterAccId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/uid/{id}")          //Get User By UserID
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable String id) {
        try {
            UserDTO user = userService.getUserByUserId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //User Account
    @GetMapping("/account")               //Get All Account
    public List<UserAccountDTO> getAllAccounts() {
        return userAccountService.getAllUserAccount();
    }

    //@PutMapping("/account/{id}")                   //Update User with Master ID
    @PostMapping("/account/{id}")              //Insert User with Master ID
    public UserAccountApproveEntity addUserAccount(@RequestBody UserAccountApproveEntity userAccountApprove, @PathVariable("id") Integer id) {
        userAccountApprove.setMaId(id);
        return userAccountApproveService.addOrUpdateUserAccountApprove_(userAccountApprove);
    }

    @PutMapping("/account/{id}")        //Delete User Account
    public String deleteUserAccount(@PathVariable("id") Integer accountNumber) {
        userAccountService.deleteUserAccount(accountNumber);
        return "Successfully deleted";
    }

    @GetMapping("/account/activate/{id}") //Activate User Account
    public UserAccountEntity activateUserAccount(@RequestBody Map<String, Object> obj, @PathVariable("id") int accountNumber) {
        String userId = (String) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        return userAccountApproveService.activateUserAccount(userId, userPin, accountNumber);
    }

    @GetMapping("/account/{id}")          //Get User By Master Account ID
    public List<UserAccountDTO> getUserAccountByMasterAccId(@PathVariable Integer id) {
        return userAccountService.getUserAccountByMasterAccId(id);

    }

    // User Contact
    @PostMapping("/contact/{uid}")              //Insert User Contact with Master Account ID
    public UserContactDTO addUserContact(@RequestBody UserContactEntity userContact, @PathVariable("uid") Integer id) {
        userContact.setMaId(id);
        return userContactService.addUserContact(userContact);
    }

    //User validation
    @GetMapping("/validation")      //validate pin
    public Boolean userValidation(@RequestBody Map<String, Object> obj) {
        String userId = (String) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        return userService.validation(userId, userPin);
    }

    //Set Default Account
    @PostMapping("/account/default")
    public UserDTO setDefaultAccount(@RequestBody Map<String, Object> obj) {
        int maId = (int) obj.get("maId");
        int defaultAccount = (int) obj.get("defaultAccount");
        return userService.setDefaultAccount(maId, defaultAccount);
    }

    //Get Default Account By Master Account ID
    @GetMapping("/account/default/{id}")
    public UserAccountDTO getDefaultAccount(@PathVariable("id") int maId) {
        return userAccountService.getDefaultAccountByMasterAccId(maId);
    }
}
