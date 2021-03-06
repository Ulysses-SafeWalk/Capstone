package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.ContactRepository;
import com.codeup.safewalk.repositories.UserRepository;
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
    private UserRepository users;

    public ContactController(UserRepository users, ContactRepository contactRepository){
        this.users = users;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contacts/{id}/create")
    public String addContactPage(@PathVariable long id, Model view){
        User user = users.findById(id);
        view.addAttribute("user", user);
        view.addAttribute("contact", new Contact());
        return "contacts/create";
    }

    @PostMapping("/contacts/{id}/create")
    public String createContact(@PathVariable long id, @ModelAttribute Contact contact){
        User user = users.findById(id);
        String number = contact.getPhoneNumber();
        String countryCode = "+1";
        String formattedNumber = countryCode.concat(number);
        contact.setPhoneNumber(formattedNumber);
        contact.setUser(user);
        contactRepository.save(contact);
        return "redirect:/profile";
    }

    @GetMapping("/contacts/{id}/delete") // Contact ID
    public String delete(@PathVariable long id) {
        contactRepository.delete(id);
        return "redirect:/profile";
    }



    @PostMapping("/contacts/edit")
    public String editContacts(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "users/profile";
    }

}
