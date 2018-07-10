package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.repositories.UserRepository;
import com.codeup.safewalk.services.LocationService;
import com.codeup.safewalk.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {
    private LocationService locationService;
    private UserService userService;

    public LocationController(LocationService locationService, UserService userService){
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/favorites/{id}")
    @ResponseBody
    public String addToFavorites(@PathVariable String id){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userService.findById(sessionUser.getId());
        Location location = locationService.findByYelpid(id);
        System.out.println(user);
        System.out.println(location);

        //get list of favorites
        //add the newFavorite to it
        //set the list of favorites
        //save the user object

        List<Location> favoriteList =  user.getFavorites();
        favoriteList.add(location);
        user.setFavorites(favoriteList);
        userService.save(user);

        return "added to favorites";
    }
}
