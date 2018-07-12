package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {


      List<Contact> findByUser(User user);

//    @Query(nativeQuery = true, value = "SELECT * FROM contacts WHERE user_id = ?")
    List<Contact> findByUser_Id(long user_id);

}

