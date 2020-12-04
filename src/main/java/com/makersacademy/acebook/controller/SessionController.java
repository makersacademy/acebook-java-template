package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dao.UserDAO;
import com.makersacademy.acebook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
public class SessionController {
  @Autowired
  UserDAO userDAO;

  @GetMapping("/getuser")
  public Map<String, Object> getLoggedInUser(Principal principal) {
    //get unique email from principal
    String email = principal.getName();
    //use that email to find the correct user
    User user = userDAO.findByEmail(email);
    //create object and add info we want to receive in the front end
    HashMap<String, Object> userMap = new HashMap<>();
    userMap.put("user", user);
    userMap.put("friends", user.getFriends());
    userMap.put("friendOf", user.getFriendOf());
    //return that object to the frontend
    return userMap;
  }
}
