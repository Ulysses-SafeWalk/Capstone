package com.codeup.safewalk.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Please enter a first name")
    private String first_name;

    @Column(nullable = false)
    @NotBlank(message = "Please enter a last name")
    private String last_name;

    @Column(nullable = false)
    @NotBlank(message = "Please enter a phone number")
    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Contact() {}

    public Contact(String first_name, String last_name, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public Contact(long id, String first_name, String last_name, String phone_number) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public void setId() { this.id = id; }

    public long getId() { return id; }

    public void setFirstName() { this.first_name = first_name; }

    public String getFirstName() { return first_name; }

    public void setLastName() { this.last_name = last_name; }

    public String getLastName() { return last_name; }

    public void setPhoneNumber() { this.phone_number = phone_number; }

    public String getPhoneNumber() { return phone_number; }
}
