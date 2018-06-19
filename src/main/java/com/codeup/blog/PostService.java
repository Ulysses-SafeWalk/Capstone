package com.codeup.blog;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts;

    public PostService() {
        posts = new ArrayList<>();
        createPosts();
    }

    private void createPosts() {
        posts.add(new Post(1, "10 things you didn't know about binary", "10 of them!"));
        posts.add(new Post(2, "How is null different than undefined different than 0 different than false", "The answers are true!"));
        posts.add(new Post(3, "hello world", "How many tutorials start off w/ hello world!"));
        posts.add(new Post(4, "Recipe for stew", "lol jk, here's a story about my family"));
        posts.add(new Post(5, "7 ways to debug spring", "You won't believe number 3"));
        posts.add(new Post(6, "One weird trick to learning programming", "Programming is a way of thinking"));
        posts.add(new Post(7, "Secret of success", "We become what we think about"));
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post findOne(long id) {
        return posts.get((int) id - 1);
    }

}
