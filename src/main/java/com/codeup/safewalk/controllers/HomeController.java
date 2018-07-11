package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.repositories.UserRepository;
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
    final UserRepository users;

    HomeController(LocationService locationService, ReviewService reviewService, UserRepository users){
        this.locationService = locationService;
        this.reviewService = reviewService;
        this.users = users;
    }

    @GetMapping("/")
    public String showHomePage(Model view) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(sessionUser != null) {
            User user = users.findById(sessionUser.getId());
            view.addAttribute("user", user);
            view.addAttribute("favorites", locationService.threeFavorites(user));
            view.addAttribute("reviews", reviewService.threeReviews(user));
        }
        return "home";
    }

}
