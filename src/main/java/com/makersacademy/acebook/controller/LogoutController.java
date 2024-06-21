package com.makersacademy.acebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LogoutController {
    @PostMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("/login");
    }
}
