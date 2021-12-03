package com.makersacademy.acebook.restcontroller;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userservice")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getusers")
    public List<User> retrieveAllUsers() {
        List<User> result=userService.retrieveAllUsersFromDB();
        return result;
    }


}
