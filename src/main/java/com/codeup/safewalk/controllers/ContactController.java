package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.ContactRepository;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    private UserRepository users;
    private ContactRepository contacts;

    public ContactController(UserRepository users, ContactRepository contacts){
        this.users = users;
        this.contacts = contacts;
    }

    // Get individual profile based on session user
    @GetMapping("/profile")
    public String getProfilePage( Model view){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.findById(sessionUser.getId());
        view.addAttribute("user", user);
        return "profiles/profile";
    }

    //add get/post for user to edit their profile

    @PostMapping("/profile/{id}/edit")
    public String editProfile( @ModelAttribute User user) {
        users.save(user);
        return "redirect:/profile";
    }
}
