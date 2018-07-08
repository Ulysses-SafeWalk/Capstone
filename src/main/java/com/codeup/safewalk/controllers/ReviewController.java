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
    private UserRepository userRepository;
    private LocationRepository locationRepository;

    // PostController constructor
    // Dependency Injection
    public ReviewController(ReviewService reviewService, UserRepository userRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.reviewService = reviewService;
        this.locationRepository = locationRepository;
    }

    // Brings up individual reviews
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
    @GetMapping("/reviews/{name}")
    public String showDetails(@PathVariable String name, Model view) {
        Location location = locationRepository.findByName(name);
        System.out.println(location.getId());
        List<Review> locationReviews = reviewService.findByLocationId(location.getId());
        view.addAttribute("location", location);
        view.addAttribute("reviews", locationReviews);
        return "reviews/location";
    }

    // Getting review edit page for 1 review
    @GetMapping("/reviews/{id}/edit") // Review id
    public String edit(@PathVariable long id, Model view) {
        view.addAttribute("review", reviewService.findOne(id));
        return "reviews/edit";
    }

    // Updating an individual review
    @PostMapping("/reviews/{id}/edit")
    public String updateReview(@PathVariable long id, @ModelAttribute Review review) {
        Location location = locationRepository.findById(id);
        reviewService.save(review, location);
        return "redirect:/reviews";
    }

    // Go to create an individual review
    @GetMapping("/reviews/create")
    public String getCreatePage(Model view) {
        // pass a new post to the view
        view.addAttribute("review", new Review());
        return "reviews/create";
    }

    // Create review and return to location review page
    @PostMapping("/reviews/create/{id}")
    public String createReview(@PathVariable long id, @ModelAttribute Review review) {
        Location location = locationRepository.findById(id);
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
