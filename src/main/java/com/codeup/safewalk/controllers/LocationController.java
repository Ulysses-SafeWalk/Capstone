package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {
    private LocationRepository locationRepository;
    private UserRepository userRepository;

    public LocationController(LocationRepository locationRepository, UserRepository userRepository){
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/favorites/{name}")
    @ResponseBody
    public String addToFavorites(@PathVariable String name){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userRepository.findById(sessionUser.getId());
        Location location = locationRepository.findByName(name);
        System.out.println(user);
        System.out.println(location);

        //get list of favorites
        //add the newFavorite to it
        //set the list of favorites
        //save the user object

        List<Location> favoriteList =  user.getFavorites();
        favoriteList.add(location);
        user.setFavorites(favoriteList);
        userRepository.save(user);

        return "added to favor";
    }
}
