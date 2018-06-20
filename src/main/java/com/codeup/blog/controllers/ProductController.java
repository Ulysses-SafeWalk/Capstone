package com.codeup.blog.controllers;

import com.codeup.blog.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/create")
    public String showCreateForm(Model view) {
        view.addAttribute("product", new Product());
        return "products/create";
    }

    @ResponseBody
    @PostMapping("/create")
    public String saveProduct(@ModelAttribute Product product) {

        System.out.println(product);

        return "check the console";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Check the console!";
    }

}
