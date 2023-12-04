package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.dto.AuthenticationRequest;
import com.casualTravel.restservice.dto.AuthenticationResponse;
import com.casualTravel.restservice.dto.RegisterRequest;
import com.casualTravel.restservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-management")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws Exception {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
