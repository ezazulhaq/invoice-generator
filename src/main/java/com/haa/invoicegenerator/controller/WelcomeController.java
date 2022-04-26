package com.haa.invoicegenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping(value = "/")
    public String getDefectList(Model theModel) {
        return "home";
    }

}
