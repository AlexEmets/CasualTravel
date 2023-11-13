package com.casualTravel.restservice.service;

import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.repository.InterestRepository;
import com.casualTravel.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterestService {
    private final InterestRepository interestRepository;
    private final UserRepository userRepository;

    @Autowired
    public InterestService(InterestRepository interestRepository, UserRepository userRepository) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
    }

    public Interest createInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    public Iterable<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    public Optional<Interest> getInterestById(Long interestId) {
        return interestRepository.findById(interestId);
    }

    public void updateInterest(Interest interest) {
        interestRepository.save(interest);
    }

    public void deleteInterest(Long interestId) {
        interestRepository.deleteById(interestId);
    }

    public void addInterestToUser(Long interestId, Long userId) {
        Interest interest = interestRepository.findById(interestId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (interest != null && user != null) {
            user.addInterest(interest);
            userRepository.save(user);
        }
    }

    public void removeInterestFromUser(Long interestId, Long userId) {
        Interest interest = interestRepository.findById(interestId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (interest != null && user != null) {
            user.removeInterest(interest);
            userRepository.save(user);
        }
    }
}