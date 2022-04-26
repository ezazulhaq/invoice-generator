package com.haa.invoicegenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "/login";
    }

    @GetMapping(value = "/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

}
