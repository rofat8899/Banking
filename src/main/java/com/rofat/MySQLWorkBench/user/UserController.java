package com.rofat.MySQLWorkBench.user;

import com.rofat.MySQLWorkBench.user.model.User;
import com.rofat.MySQLWorkBench.user.model.UserAccount;
import com.rofat.MySQLWorkBench.user.model.UserContact;
import com.rofat.MySQLWorkBench.user.service.UserAccountService;
import com.rofat.MySQLWorkBench.user.service.UserContactService;
import com.rofat.MySQLWorkBench.user.service.UserService;
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

    //User

    @GetMapping()               //Get All Users
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping()              //Insert User
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/{id}")          //Get User By Master Account ID
    public ResponseEntity<User> getUserByMasterAccId(@PathVariable Integer id) {
        try {
            User user= userService.getUserByMasterAccId(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/uid/{id}")          //Get User By UserID
    public ResponseEntity<User> getUserByUserId(@PathVariable Integer id) {
        try {
            User user= userService.getUserByUserId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //User Account

    @GetMapping("/account")               //Get All Account
    public List<UserAccount> getAllAccounts(){
        return userAccountService.getAllUserAccount();
    }

    @PutMapping("/account/{uid}")                   //Update User
    @PostMapping("/account/{uid}")              //Insert User
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount, @PathVariable("uid") Integer id){
        userAccount.setMaId(id);
        return userAccountService.addUserAccount_(userAccount);
    }

    @GetMapping("/account/{id}")          //Get User By Master Account ID
    public List<UserAccount> getUserAccountByMasterAccId(@PathVariable Integer id) {
      return userAccountService.getUserAccountByMasterAccId(id);

    }

    // User Contact

    @PostMapping("/contact/{uid}")              //Insert User Contact
    public UserContact addUserContact(@RequestBody UserContact userContact, @PathVariable("uid") Integer id){
        userContact.setMaId(id);
        return userContactService.addUserContact(userContact);
    }

    //User validation

    @GetMapping("/validation")      //validate pin
    public Boolean userValidation(@RequestBody Map<String, Object> obj)
    {
        int userId = (int) obj.get("userId");
        String userPin = (String) obj.get("userPin");
        return userService.validation(userId,userPin);
    }

    //Set Default Account
    @PostMapping("/account/default")
    public User setDefaultAccount(@RequestBody Map<String,Object> obj)
    {
        int maId = (int) obj.get("maId");
        int defaultAccount = (int) obj.get("defaultAccount");
        return userService.setDefaultAccount(maId,defaultAccount);
    }

    //Get Default Account By Master Account ID
    @GetMapping("/account/default/{id}")
    public UserAccount getDefaultAccount(@PathVariable("id") int maId)
    {
        return userService.getDefaultAccount(maId);
    }
}
