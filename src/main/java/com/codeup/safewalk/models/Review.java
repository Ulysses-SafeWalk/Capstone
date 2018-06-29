package com.codeup.safewalk.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Your review must have a title")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Your review must have at least one sentence")
    private String body;

    @Column
    private int rating;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review() {}

    public Review(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Review(String title, String body, int rating) {
        this.title = title;
        this.body = body;
        this.rating = rating;
    }

    public Review(long id, String title, String body, int rating){
        this.id = id;
        this.title = title;
        this.body = body;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocation() { this.location = location; }

    public Location getLocation() { return location; }

    public void setRating() { this.rating = rating; }

    public int getRating() { return rating; }
}
