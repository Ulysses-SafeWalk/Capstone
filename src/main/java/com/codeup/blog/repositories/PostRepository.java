package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();

    List<Post> findByTitle(String title);
}
