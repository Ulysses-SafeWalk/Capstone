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
    private String yelpid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String yelpcategory;
    @Column(nullable = false)
    private String latitude;
    @Column(nullable = false)
    private String longitude;


    public Location(String yelpid, String name, String category, String yelpcategory, String latitude, String longitude) {
        this.yelpid = yelpid;
        this.name = name;
        this.category = category;
        this.yelpcategory = yelpcategory;
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

    public String getyelpid() {
        return yelpid;
    }

    public void setyelpid(String yelpid) {
        this.yelpid = yelpid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getyelpcategory() {
        return yelpcategory;
    }

    public void setyelpcategory(String yelpcategory) {
        this.yelpcategory = yelpcategory;
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
