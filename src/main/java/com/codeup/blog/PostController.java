package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    public @ResponseBody String index() {
        return "Here are all the ads!";
    }
    @GetMapping("/posts/{id}")
    public @ResponseBody String showDetails(@PathVariable long id) {
        return "Viewing post #" + id;
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
}
