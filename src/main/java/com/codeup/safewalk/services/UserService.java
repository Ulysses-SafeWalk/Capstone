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

    public User findById(long id) { return userRepository.findById(id); }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }
}
