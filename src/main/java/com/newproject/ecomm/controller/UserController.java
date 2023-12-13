package com.newproject.ecomm.controller;

import com.newproject.ecomm.model.User;
import com.newproject.ecomm.model.UserDTO;
import com.newproject.ecomm.service.UserLoginService;
import com.newproject.ecomm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserService userService;

    //User Registration
    @PostMapping("/registration")

    public ResponseEntity<User> saveUser(@Valid @RequestBody User user)
    {
        User userResponse = userService.saveUser(user);
        return new ResponseEntity<User>(userResponse, HttpStatus.CREATED);
    }

    //User Login
    @PostMapping("/login")
    public ResponseEntity<String> userLoginContro(@Valid @RequestBody UserDTO userDTO){
        String msg = userLoginService.userLogin(userDTO);
        return  new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
    }

    //Update user
    @PutMapping("/updateUser/{key}")
    public ResponseEntity<User> updateUserCreds(@Valid @RequestBody User user, @PathVariable String key){
        User userResponse = userService.updateUserCreds(user, key);
        return new ResponseEntity<User>(userResponse, HttpStatus.ACCEPTED);

    }


    //Logout user
    @PostMapping("/logout/{key}")
    public ResponseEntity<String> userLogout(@Valid @PathVariable String key){
        String msg = userService.userLogOut(key);
        return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);

    }

}
