package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Profile;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.ProfileRepository;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ProfilesController {

    @Autowired
    UserRepository urepository;
    @Autowired
    ProfileRepository prepository;
   

    @PostMapping("/profiles")
    public RedirectView create(@ModelAttribute Profile profileModel, Principal principal) {
        String userName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(userName);
        User user = currentUser.get();
        Long userIdLong = user.getId();
        Optional<Profile> profileOptional = prepository.findByUserId(userIdLong);
        if (profileOptional.isPresent()) {
          Profile profile = profileOptional.get();
          prepository.delete(profile);
        }
        profileModel.setUserId(userIdLong);
        prepository.save(profileModel);

        return new RedirectView(String.format("/users/%s", userName));
    }
}
