package com.codeup.safewalk.services;

import com.codeup.safewalk.models.Post;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.PostRepository;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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

        // get the user from the session
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userRepository.findById(sessionUser.getId());

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
