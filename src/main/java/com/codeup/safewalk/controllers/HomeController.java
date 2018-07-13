package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.TwilioData;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.ContactRepository;
import com.codeup.safewalk.repositories.UserRepository;
import com.codeup.safewalk.services.LocationService;
import com.codeup.safewalk.services.ReviewService;
import com.codeup.safewalk.services.TwilioTexter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    final LocationService locationService;
    final ReviewService reviewService;
    final UserRepository users;
    final ContactRepository contacts;
    TwilioTexter texter;

    HomeController(LocationService locationService, ReviewService reviewService, UserRepository users, TwilioTexter texter, ContactRepository contacts){
        this.locationService = locationService;
        this.reviewService = reviewService;
        this.users = users;
        this.texter = texter;
        this.contacts = contacts;
    }

    @GetMapping("/")
    public String showHomePage(Model view) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = users.findById(sessionUser.getId());
            System.out.println(user);
            view.addAttribute("user", user);
            view.addAttribute("favorites", locationService.threeFavorites(user));
            view.addAttribute("reviews", reviewService.threeReviews(user));
        }
        return "home";
    }

    @PostMapping(value="/")
    @ResponseBody
    public  void  fireButtons(
                            @RequestParam(name = "latitude") String latitude,
                            @RequestParam(name = "longitude") String longitude,
                            @RequestParam(name = "buttonType") String buttonType
    ){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.findById(sessionUser.getId());

        texter.go(latitude, longitude, user, buttonType);
    }
}
