package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/{userId}/interests/{interestId}")
    public void addUserInterest(@PathVariable Long userId, @PathVariable Long interestId) {
        userService.addInterestToUser(userId, interestId);
    }

    @DeleteMapping("/{userId}/interests/{interestId}")
    public void removeUserInterest(@PathVariable Long userId, @PathVariable Long interestId) {
        userService.removeInterestFromUser(userId, interestId);
    }
}