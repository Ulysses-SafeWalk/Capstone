package com.codeup.safewalk.services;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.repositories.ReviewRepository;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review save(Review review, Location location) {

        // get the user from the session
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // make sure to get the actual, real deal user from the DB.
        User user = userRepository.findById(sessionUser.getId());
        review.setLocation(location);
        review.setUser(user);
        reviewRepository.save(review);
        return review;
    }


    public List<Review> findAllFromUser() {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(sessionUser.getId());

        return reviewRepository.findByUser(user);
    }

    public List<Review> threeReviews(User user) {
        return reviewRepository.threeReviews(user.getId());
    }

    public int getOverallReview(Review review){
        int parkingRate = review.getParking_rating();
        int sidewalkRate = review.getSidewalk_rating();
        int familyRate = review.getFamily_rating();
        int crowdRate = review.getCrowd_rating();
        int transportRate = review.getTransport_rating();
        int lightRate = review.getLighting_rating();
        int feelRate = review.getFeeling_rating();
        int averageRating;
        if (transportRate == 0){
            averageRating = ((parkingRate + sidewalkRate + familyRate + crowdRate + lightRate + feelRate)/6);
        } else {
            averageRating = ((parkingRate + sidewalkRate + familyRate + crowdRate + transportRate + lightRate + feelRate)/7);
        }
        return averageRating;
    }

    public Review findOne(long id) {
        return reviewRepository.findOne(id);
    }

    public List<Review> searchByTitle(String title) {
        return reviewRepository.findByTitle(title);
    }

    public void delete(long id) {
        reviewRepository.delete(id);
    }

    public List<Review> findByLocationId(long id) { return reviewRepository.findByLocationId(id);}

    public List<Review> search(String searchTerm){
        return reviewRepository.search("%" + searchTerm + "%");
    }
}
