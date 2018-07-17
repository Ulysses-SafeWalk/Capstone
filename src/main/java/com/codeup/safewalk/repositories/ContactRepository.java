package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByUser(User user);

    List<Contact> findByUser_Id(long user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM contacts WHERE user_id = ?1 LIMIT 3")
    List<Contact> findAllFromUser(long user_id);

    Contact save(Contact contact);

    Contact findById(long id);

    void delete(long id);

//    this is a thing

}

