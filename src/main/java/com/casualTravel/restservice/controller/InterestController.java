package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interests")
public class InterestController {
    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PostMapping
    public Interest createInterest(@RequestBody Interest interest) {
        return interestService.createInterest(interest);
    }

    @GetMapping
    public Iterable<Interest> getAllInterests() {
        return interestService.getAllInterests();
    }

    @GetMapping("/{interestId}")
    public Interest getInterestById(@PathVariable Long interestId) {
        return interestService.getInterestById(interestId).orElse(null);
    }

    @PutMapping("/{interestId}")
    public void updateInterest(@PathVariable Long interestId, @RequestBody Interest interest) {
        interestService.updateInterest(interestId, interest);
    }

    @DeleteMapping("/{interestId}")
    public void deleteInterest(@PathVariable Long interestId) {
        interestService.deleteInterest(interestId);
    }

    @PostMapping("/{interestId}/users/{userId}")
    public void addInterestToUser(@PathVariable Long interestId, @PathVariable Long userId) {
        interestService.addInterestToUser(interestId, userId);
    }

    @DeleteMapping("/{interestId}/users/{userId}")
    public void removeInterestFromUser(@PathVariable Long interestId, @PathVariable Long userId) {
        interestService.removeInterestFromUser(interestId, userId);
    }
}