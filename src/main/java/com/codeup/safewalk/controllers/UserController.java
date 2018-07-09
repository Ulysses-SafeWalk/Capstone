package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getProfilePage(@ModelAttribute User user, Model view){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = users.findById(sessionUser.getId());
        view.addAttribute("user", user);
        return "profile";
    }

    //add get/post for user to edit their profile

    @PostMapping("/profile")
    public String editProfile() {
        return "profile";
    }


}