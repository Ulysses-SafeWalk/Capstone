package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.ContactRepository;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {
   private UserRepository users;
    private ContactRepository contactRepository;
    private Contact contact;

    public ContactController(UserRepository users, ContactRepository contactRepository, Contact contact){
        this.users = users;
        this.contactRepository = contactRepository;
        this.contact = contact;
    }

    // Get individual profile on Register page
    @GetMapping("/contacts/{id}/")
    public String getUserContacts(Model view, @PathVariable long id){
        List<Contact> contacts = contactRepository.findByUser_Id(id);
        view.addAttribute("contacts", contacts);
        return "profiles/profile";
    }

    //add get/post for user to edit their profile

    @PostMapping("/contacts/{id}/edit")
    public String editContacts( @ModelAttribute List<Contact> contacts) {
        contactRepository.save(contacts);
        return "redirect:/profile";
    }
}
