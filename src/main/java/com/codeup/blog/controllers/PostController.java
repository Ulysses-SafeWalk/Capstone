package com.codeup.blog.controllers;

import com.codeup.blog.PostService;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

    private PostService postService;

    // PostController constructor
    // Dependency Injection
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // mappings are the url
    @GetMapping("/posts")
    public String index(Model view) {

        List<Post> posts = postService.findAll();

        view.addAttribute("posts", posts);

        // relative path for the .html file inside of resources/templates w/o the .html
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String showDetails(@PathVariable long id, Model view) {

        Post post = postService.findOne(id);

        view.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model view) {
//        Post existingPost = postService.findOne(id);
//
//        view.addAttribute("post", existingPost);

        view.addAttribute("post", postService.findOne(id));

        return "posts/edit";
    }

    @GetMapping("/posts/create")
    public String create(Model view) {
        // pass a new post to the view
        view.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);

        return "redirect:/posts";
    }

}