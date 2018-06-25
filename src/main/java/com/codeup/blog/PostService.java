package com.codeup.blog;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post save(Post post) {
        User user = userRepository.first();
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    public Post findOne(long id) {
        return postRepository.findOne(id);
    }

    public List<Post> searchByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public void delete(long id) {
        postRepository.delete(id);
    }

    public List<Post> search(String searchTerm) {
        return postRepository.search("%" + searchTerm + "%");
    }
}
