package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Product;
import com.codeup.safewalk.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/create")
    public String showCreateForm(Model view) {
        view.addAttribute("product", new Product());
        return "products/create";
    }

    @ResponseBody
    @PostMapping("/create")
    public String saveProduct(@ModelAttribute Product product) {

        productRepository.save(product);

        return "check the console";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Check the console!";
    }

}
