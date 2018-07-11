package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.services.LocationService;
import com.codeup.safewalk.services.ReviewService;
import com.codeup.safewalk.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class HomeController {
    final LocationService locationService;
    final ReviewService reviewService;

    HomeController(LocationService locationService, ReviewService reviewService){
        this.locationService = locationService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String showHomePage(Model view) {
        view.addAttribute("favorites", locationService.threeFavorites());
        view.addAttribute("reviews", reviewService.threeReviews());
        return "home";
    }

}
