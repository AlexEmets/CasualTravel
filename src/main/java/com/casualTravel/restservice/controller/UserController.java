package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.service.JwtService;
import com.casualTravel.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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

    @GetMapping("/me")
    public User getUserByToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // "Bearer ".length()

            String userEmail = jwtService.extractEmail(token);

            return (User) userService.loadUserByUsername(userEmail);
        }
        System.err.println("Token not found");
        return null;
    }


}