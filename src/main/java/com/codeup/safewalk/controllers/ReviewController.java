package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.models.Review;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.services.ReviewService;
import com.codeup.safewalk.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {

    private ReviewService reviewService;
    private UserRepository users;
    private LocationRepository locationRepository;

    // PostController constructor
    // Dependency Injection
    public ReviewController(ReviewService reviewService, UserRepository userRepository, LocationRepository locationRepository) {
        this.users = userRepository;
        this.reviewService = reviewService;
        this.locationRepository = locationRepository;
    }

    // Brings up users reviews
    @GetMapping("/reviews")
    public String index(Model view, @RequestParam(name = "search", required = false) String searchTerm) {
        // if there's a search term, show results for that search
        // else, just show all the reviews
        System.out.println("ReviewController#index");
        List<Review> reviews;
        if (searchTerm == null) {
            reviews = reviewService.findAllFromUser();
        } else {
            reviews = reviewService.search(searchTerm);
        }

        view.addAttribute("reviews", reviews);
        view.addAttribute("searchTerm", searchTerm);

        // relative path for the .html file inside of resources/templates w/o the .html
        return "reviews/index";
    }

    // This method returns all reviews for an individual location
    @GetMapping("/reviews/{id}")
    public String showReviewsForLocation(@PathVariable String id, Model view) {
        Location location = locationRepository.findByYelpid(id);
        System.out.println(location.getId());
        List<Review> locationReviews = reviewService.findByLocationId(location.getId());
        view.addAttribute("location", location);
        view.addAttribute("reviews", locationReviews);
        return "reviews/location";
    }

    //shows single review
    @GetMapping("/reviews/{reviewId}/detail")
    public String showDetail(@PathVariable long reviewId, Model view){
        Review review = reviewService.findOne(reviewId);
        view.addAttribute("review", review);
        return "reviews/detail";
    }

    // Getting review edit page for 1 review
    @GetMapping("/reviews/{id}/edit") // Review id
    public String edit(@PathVariable long id, Model view) {
        Review review = reviewService.findOne(id);
        System.out.println("review id is: " + id);
        view.addAttribute("review", review);
        return "reviews/edit";
    }

    // Updating an individual review
    @PostMapping("/reviews/{id}/edit")
    public String updateReview(@PathVariable long id, @ModelAttribute Review review) {
        Review editedReview = reviewService.findOne(id);
        Location location = editedReview.getLocation();
        int averageRating = reviewService.getOverallReview(review);
        review.setOverall_rating(averageRating);
        reviewService.save(review, location);
        return "redirect:/reviews";
    }

    // Go to create an individual review
    @GetMapping("/reviews/{id}/create")
    public String getCreatePage(@PathVariable long id, Model view) {
        view.addAttribute("location", locationRepository.findById(id));
        view.addAttribute("review", new Review());
        return "reviews/create";
    }

    // Create review and return to location review page
    @PostMapping("/reviews/{id}/create")
    public String createReview(@PathVariable long id, @ModelAttribute Review review) {
        Location location = locationRepository.findById(id);
        System.out.println(location.getName());
        int averageRating = reviewService.getOverallReview(review);
        review.setOverall_rating(averageRating);
        reviewService.save(review, location);
        return "redirect:/reviews";
    }

    // Delete a review
    @GetMapping("/reviews/{id}/delete") // Review ID
    public String delete(@PathVariable long id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }

}
