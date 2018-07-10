package com.codeup.safewalk.services;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    final LocationRepository locationRepository;
    final UserService userService;

    LocationService(LocationRepository locationRepository, UserService userService){
        this.locationRepository = locationRepository;
        this.userService = userService;
    }

    public List<Location> findAll() { return locationRepository.findAll();}
    public Location findById(long id){ return locationRepository.findById(id);}
    public Location findByName(String name){ return locationRepository.findByName(name);}
    public Location findByYelpid(String id){ return locationRepository.findByYelpid(id);}
    public List<Location> threeFavorites(){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(sessionUser.getId());
        return locationRepository.threeFavorites(user.getId());
    }
}
