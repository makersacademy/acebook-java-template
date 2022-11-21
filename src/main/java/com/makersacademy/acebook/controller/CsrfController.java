package com.makersacademy.acebook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CsrfController {
    @RequestMapping(value="/csrf-token", method=RequestMethod.GET)
    public @ResponseBody String getCsrfToken(HttpServletRequest request) {
    CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
    System.out.println(token.getToken());
    return token.getToken();
}
}
