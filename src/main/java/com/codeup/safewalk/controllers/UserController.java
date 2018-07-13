package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.ContactRepository;
import com.codeup.safewalk.repositories.UserRepository;
import com.codeup.safewalk.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private ContactRepository contactRepository;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, ContactRepository contactRepository) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.contactRepository = contactRepository;
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

        List<Contact> contacts = contactRepository.findByUser_Id(user.getId());
        contactRepository.save(contacts);
        return "redirect:/login";
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

    @GetMapping("/profile/updatePassword")
    public String getPasswordUpdateForm(Model view){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.findById(currentUser.getId());
        view.addAttribute("userPasswordUpdate", currentUser);
        System.out.println(currentUser.getUsername());
        return "users/password";
    }

    @PostMapping("/profile/updatePassword")
    public String updatePassword(@PathVariable long id, Model view, Errors errors, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword){
        User currentUser = users.findById(id);
        if(!passwordEncoder.matches(currentUser.getPassword(), oldPassword)) {
            System.out.println("passwords don't match");
        }
        if(!newPassword.equals(confirmPassword)){
            System.out.println("New passwords don't match");
        }
        view.addAttribute("user", currentUser);
        currentUser.setPassword(passwordEncoder.encode((newPassword)));
        users.save(currentUser);
        return "redirect:/profile";
    }


}