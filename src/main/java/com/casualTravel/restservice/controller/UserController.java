package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.dto.UserDTO;
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

    @GetMapping("/me")
    public UserDTO getUserByToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // "Bearer ".length()

            String userEmail = jwtService.extractEmail(token);
            User user = (User) userService.loadUserByUsername(userEmail);

            return UserDTO.mapToUserDTO(user);
        }
        System.err.println("Token not found");
        return null;
    }
}