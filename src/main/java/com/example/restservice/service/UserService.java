package com.example.restservice.service;

import com.example.restservice.dto.AuthenticationRequest;
import com.example.restservice.dto.AuthenticationResponse;
import com.example.restservice.dto.RegisterRequest;
import com.example.restservice.models.Interest;
import com.example.restservice.models.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
//  private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    @Autowired
//    public UserService(UserRepository userRepository, InterestRepository interestRepository) {
//        this.userRepository = userRepository;
//        this.interestRepository = interestRepository;
//    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void addInterestToUser(Long userId, Long interestId) {
        User user = userRepository.findById(userId).orElse(null);
        Interest interest = interestRepository.findById(interestId).orElse(null);

        if (user != null && interest != null) {
            user.addInterest(interest);
            userRepository.save(user);
        }
    }

    public void removeInterestFromUser(Long userId, Long interestId) {
        User user = userRepository.findById(userId).orElse(null);
        Interest interest = interestRepository.findById(interestId).orElse(null);

        if (user != null && interest != null) {
            user.removeInterest(interest);
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        //var refreshToken = jwtService.generateRefreshToken(user);
        //saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                //.refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                //.refreshToken(refreshToken)
                .build();
    }
}