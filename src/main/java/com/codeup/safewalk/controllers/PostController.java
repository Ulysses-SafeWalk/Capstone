package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.services.PostService;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private UserRepository userRepository;

    // PostController constructor
    // Dependency Injection
    public PostController(PostService postService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.postService = postService;
    }

    // mappings are the url
    @GetMapping("/posts")
    public String index(Model view, @RequestParam(name = "search", required = false) String searchTerm) {
        // if there's a search term, show results for that search
        // else, just show all the reviews
        System.out.println("PostController#index");
        List<Review> reviews;
        if (searchTerm == null) {
            reviews = postService.findAll();
        } else {
            reviews = postService.search(searchTerm);
        }

        view.addAttribute("posts", reviews);
        view.addAttribute("searchTerm", searchTerm);

        // relative path for the .html file inside of resources/templates w/o the .html
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String showDetails(@PathVariable long id, Model view) {

        System.out.println("PostController#showDetails");
        Review review = postService.findOne(id);

        view.addAttribute("post", review);

        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model view) {
        view.addAttribute("post", postService.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Review review) {
        postService.save(review);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/create")
    public String create(Model view) {
        // pass a new post to the view
        view.addAttribute("post", new Review());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(@ModelAttribute Review review) {
        // inside of the service the user property is set

        postService.save(review);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postService.delete(id);
        return "redirect:/posts";
    }


}
