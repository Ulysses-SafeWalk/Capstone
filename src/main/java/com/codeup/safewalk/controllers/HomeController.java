package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class HomeController {
    final LocationRepository locationRepository;

    HomeController(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model view) {
        view.addAttribute("locations", locationRepository.findAll());
        return "home";
    }

}
