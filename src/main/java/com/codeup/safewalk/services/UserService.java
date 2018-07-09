package com.codeup.safewalk.services;

import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository users) {
        this.userRepository = users;
    }

    public User save(User user) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findById(sessionUser.getId());
        userRepository.save(user);
        return user;
    }
}
