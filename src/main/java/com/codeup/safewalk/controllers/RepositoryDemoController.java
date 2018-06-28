package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Product;
import com.codeup.safewalk.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RepositoryDemoController {
    private ProductRepository repository;

    public RepositoryDemoController(ProductRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping("/product-test")
    public String test() {

        long count = repository.count();

        System.out.format("There are %d products in the DB.\n", count);

        Iterable<Product> products = repository.findAll();

        for (Product product : products) {
            System.out.println(product);
        }

        return "check the console";
    }

}
