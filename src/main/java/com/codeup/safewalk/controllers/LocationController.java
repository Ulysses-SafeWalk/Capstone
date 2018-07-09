package com.codeup.safewalk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @PostMapping("/favorites/{name}")
    public String addToFavorites(@PathVariable String name){
        return "home";
    }
}
