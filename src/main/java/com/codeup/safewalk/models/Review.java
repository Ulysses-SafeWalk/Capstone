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

    @Column(nullable = false)
    private int parking_rating;

    @Column(nullable = false)
    private int sidewalk_rating;

    @Column(nullable = false)
    private int family_rating;

    @Column(nullable = false)
    private int crowd_rating;

    @Column
    private int transport_rating;

    @Column(nullable = false)
    private int lighting_rating;

    @Column(nullable = false)
    private int feeling_rating;

    @Column(nullable = false)
    private int overall_rating;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review() {}


    public Review(String title, String body, int parking_rating, int sidewalk_rating, int family_rating, int crowd_rating, int transport_rating, int lighting_rating, int feeling_rating, int overall_rating) {
        this.title = title;
        this.body = body;
        this.parking_rating = parking_rating;
        this.sidewalk_rating = sidewalk_rating;
        this.family_rating = family_rating;
        this.crowd_rating = crowd_rating;
        this.transport_rating = transport_rating;
        this.lighting_rating = lighting_rating;
        this.feeling_rating = feeling_rating;
        this.overall_rating = overall_rating;
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

    public void setLocation(Location location) { this.location = location; }

    public Location getLocation() { return location; }

    public String getLocationName() { return location.getName(); }

    public int getParking_rating() {
        return parking_rating;
    }

    public void setParking_rating(int parking_rating) {
        this.parking_rating = parking_rating;
    }

    public int getSidewalk_rating() {
        return sidewalk_rating;
    }

    public void setSidewalk_rating(int sidewalk_rating) {
        this.sidewalk_rating = sidewalk_rating;
    }

    public int getFamily_rating() {
        return family_rating;
    }

    public void setFamily_rating(int family_rating) {
        this.family_rating = family_rating;
    }

    public int getCrowd_rating() {
        return crowd_rating;
    }

    public void setCrowd_rating(int crowd_rating) {
        this.crowd_rating = crowd_rating;
    }

    public int getTransport_rating() {
        return transport_rating;
    }

    public void setTransport_rating(int transport_rating) {
        this.transport_rating = transport_rating;
    }

    public int getLighting_rating() {
        return lighting_rating;
    }

    public void setLighting_rating(int lighting_rating) {
        this.lighting_rating = lighting_rating;
    }

    public int getFeeling_rating() {
        return feeling_rating;
    }

    public void setFeeling_rating(int feeling_rating) {
        this.feeling_rating = feeling_rating;
    }

    public int getOverall_rating() {
        return overall_rating;
    }

    public void setOverall_rating(int overall_rating) {
        this.overall_rating = overall_rating;
    }
}
