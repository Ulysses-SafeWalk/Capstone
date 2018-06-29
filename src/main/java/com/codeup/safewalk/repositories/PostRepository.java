package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Review, Long> {
    List<Review> findAll();

    List<Review> findByTitle(String title);

    // SELECT * FROM posts WHERE title LIKE '%...%' OR body like '%..%';

    List<Review> findByTitleLike(String searchTerm);

    List<Review> findByTitleLikeOrBodyLike(String titleSearch, String bodySearch);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE title LIKE ?1 OR body LIKE ?1")
    List<Review> search(String searchTerm);
}
