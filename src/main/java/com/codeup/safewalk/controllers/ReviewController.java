package com.codeup.safewalk.controllers;

import com.codeup.safewalk.models.Review;
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

    // PostController constructor
    // Dependency Injection
    public ReviewController(ReviewService reviewService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.reviewService = reviewService;
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
    @GetMapping("/reviews/{id}")
    public String showDetails(@PathVariable long id, Model view) {
        List<Review> locationReviews = reviewService.findByLocationId(id);
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
        reviewService.save(review);
        return "redirect:/reviews";
    }

    // Go to create an individual review
    @GetMapping("/reviews/create")
    public String getCreatePage(Model view) {
        // pass a new post to the view
        view.addAttribute("review", new Review());
        return "reviews/create";
    }

    // Create review and return to loation review page
    @PostMapping("/reviews/create")
    public String createReview(@ModelAttribute Review review) {
        reviewService.save(review);
        return "redirect:/reviews";
    }

    // Delete a review
    @PostMapping("/reviews/{id}/delete") // Review ID
    public String delete(@PathVariable long id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }


}
