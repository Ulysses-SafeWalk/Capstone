package com.codeup.blog.controllers;

import com.codeup.blog.models.Product;
import com.codeup.blog.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
