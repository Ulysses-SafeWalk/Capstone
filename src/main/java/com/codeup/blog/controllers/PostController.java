package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

    // mappings are the url
    @GetMapping("/posts")
    public String index(Model view) {
        List<Post> aListOfPosts = makeSomePosts();
        view.addAttribute("posts", aListOfPosts);

        // relative path for the .html file inside of resources/templates w/o the .html
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showDetails(@PathVariable long id, Model view) {

        Post post = new Post(id,"10 weird tricks to learning binary", "Don't worry, I'll stop making binary jokes.");

        view.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public @ResponseBody String edit(@PathVariable long id) {
        return "View the form for editing post #" + id;
    }

    @GetMapping("/posts/create")
    public @ResponseBody String create() {
        return "View the form for creating a post";
    }
    @PostMapping("/posts/create")
    public @ResponseBody String savePost() {
        return "saving to the database...";
    }

    private List<Post> makeSomePosts() {
        return Arrays.asList(
            new Post("Recipe for stew", "lol jk, here's a story about my family"),
            new Post("7 ways to debug spring", "You won't believe number 3")
        );
    }
}
