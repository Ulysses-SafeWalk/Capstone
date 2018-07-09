package com.codeup.safewalk.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String yelp_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String yelp_category;
    @Column(nullable = false)
    private String latitude;
    @Column(nullable = false)
    private String longitude;


    public Location(String yelp_id, String name, String category, String yelp_category, String latitude, String longitude) {
        this.yelp_id = yelp_id;
        this.name = name;
        this.category = category;
        this.yelp_category = yelp_category;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    @OneToMany(mappedBy = "location")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "user_location",
            joinColumns = {@JoinColumn(name = "location_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> userFavorites;

    public Location() {};


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYelp_id() {
        return yelp_id;
    }

    public void setYelp_id(String yelp_id) {
        this.yelp_id = yelp_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYelp_category() {
        return yelp_category;
    }

    public void setYelp_category(String yelp_category) {
        this.yelp_category = yelp_category;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<User> getFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(List<User> userFavorites) {
        this.userFavorites = userFavorites;
    }
}
