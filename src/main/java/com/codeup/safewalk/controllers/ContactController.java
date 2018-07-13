package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.Review;
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
    private ContactRepository contactRepository;

    public ContactController(UserRepository users, ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contacts/{id}/edit")
    public String editContacts( @ModelAttribute List<Contact> contacts) {
        contactRepository.save(contacts);
        return "profiles/profile";
    }

}
