package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.UserRepository;
import com.codeup.safewalk.services.LocationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {
    private LocationService locationService;
    private UserRepository userRepository;

    public LocationController(LocationService locationService, UserRepository userRepository){
        this.locationService = locationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/favorites/{id}")
    @ResponseBody
    public String addToFavorites(@PathVariable String id){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userRepository.findById(sessionUser.getId());
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
        userRepository.save(user);

        return "added to favorites";
    }

    @GetMapping("/favorites/index")
    public String viewAllFavorites(Model view){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userRepository.findById(sessionUser.getId());
        List<Location> favorites = locationService.allFavorites(user);

        view.addAttribute("favorites", favorites);
        return "locations/index";
    }

    @GetMapping("/favorites/{yelpid}/delete") // Location ID
    public String delete(@PathVariable String yelpid) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userRepository.findById(sessionUser.getId());
        Location location = locationService.findByYelpid(yelpid);

        List<Location> userFavorites = user.getFavorites();
        userFavorites.remove(location);
        user.setFavorites(userFavorites);
        userRepository.save(user);
        return "redirect:/favorites/index";
    }

}
