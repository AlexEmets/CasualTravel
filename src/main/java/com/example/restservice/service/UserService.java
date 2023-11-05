package com.example.restservice.service;

import com.example.restservice.models.Interest;
import com.example.restservice.models.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final InterestRepository interestRepository;

    @Autowired
    public UserService(UserRepository userRepository, InterestRepository interestRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

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
}