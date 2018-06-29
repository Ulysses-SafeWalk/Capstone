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
    private String name;
    @Column(nullable = false, unique = true)
    private String address;

    @OneToMany(mappedBy = "location")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "user_location",
            joinColumns = {@JoinColumn(name = "location_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<UserLocation> favorites;

    public Location() {};

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    };

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<UserLocation> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<UserLocation> favorites) {
        this.favorites = favorites;
    }
}
