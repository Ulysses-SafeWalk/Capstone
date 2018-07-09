package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();
    Location findById(long id);
    Location findByName(String name);
    Location findByYelpid(String id);
}
