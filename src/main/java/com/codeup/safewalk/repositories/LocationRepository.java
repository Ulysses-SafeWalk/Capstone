package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.User;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findById(long id);
}
