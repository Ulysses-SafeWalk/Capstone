package com.codeup.safewalk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

}
