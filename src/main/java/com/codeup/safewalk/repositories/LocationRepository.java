package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();
    Location findById(long id);
    Location findByName(String name);
    Location findByYelpid(String id);


    @Query(nativeQuery = true, value = "SELECT * FROM locations l JOIN user_location ul ON l.id = ul.location_id WHERE ul.user_id = ?1 ORDER BY id DESC LIMIT 3")
    List<Location> threeFavorites(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM locations l JOIN user_location ul ON l.id = ul.location_id WHERE ul.user_id = ?1")
    List<Location> allFavorites(long id);

}
