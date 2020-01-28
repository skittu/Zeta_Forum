package com.demigod.Zeta_Forum.User;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/User/Registration")
    public UserReturn Register(@RequestBody Theuser user)
    {
    return userService.createAccount(user);
    }

    @RequestMapping(value="/User/Login")
    public UserReturn Login(@RequestBody Theuser user)
    {
        return userService.Login(user);
    }

    @DeleteMapping(value = "/User/Delete")
    public String Delete(@RequestParam String userId,String password)
    {
        return userService.deleteAccount(userId,password);
    }

    @PutMapping(value = "/User/changePassword")
    public String forgotPassword(@RequestParam Theuser user)
    {
        return userService.forgotPassword(user);
    }

    @GetMapping(value = "/User/checkemail")
    public String checkmail(@RequestParam String email)
    {
        return userService.validateEmail(email);
    }

    @GetMapping(value = "/User/checkusername")
    public String checkusername(@RequestParam String userName)
    {
        return userService.validateUserName(userName);
    }



}
