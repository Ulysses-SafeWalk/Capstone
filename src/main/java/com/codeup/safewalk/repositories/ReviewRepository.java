package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAll();

    List<Review> findByTitle(String title);

    List<Review> findByUser(User user);

    List<Review> findByLocationId(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM reviews WHERE title LIKE ?1 OR body LIKE ?1")
    List<Review> search(String searchTerm);

    @Query(nativeQuery = true, value = "SELECT * FROM reviews WHERE user_id = ?1 LIMIT 3")
    List<Review> threeReviews(long id);

}
